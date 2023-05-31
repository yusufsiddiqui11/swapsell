package com.stackroute.userservice.service;

import com.stackroute.userservice.configuration.MessageConfiguration;
import com.stackroute.userservice.configuration.UserDTO;
import com.stackroute.userservice.domain.Product;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.ProductDoesNotExistsException;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserServiceRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserServiceRepository userServiceRepository;
    private static final Random random = new Random();

    @Autowired
    public UserServiceImpl(UserServiceRepository userServiceRepository) {
        this.userServiceRepository = userServiceRepository;
    }

    boolean checkExistingUser(User user){
        Optional<User> userByEmail = userServiceRepository.findUserByEmail(user.getEmail());
        return userByEmail.isPresent();
    }

    public static long generateId() {
        long id = random.nextLong();
        if (id == Long.MIN_VALUE) {
            id = 0L; // To avoid negative value for id
        } else {
            id = Math.abs(id);
        }
        return id;
    }

    @RabbitListener(queues = MessageConfiguration.queueName1)
    public void userDataFromAuthService(UserDTO userDTO) throws UserAlreadyExistsException {
        registerUserToApplication(userDTO);
    }

    @Override
    public User registerUserToApplication(UserDTO userDTO) throws UserAlreadyExistsException {
        User user = new User();
        user.setId(generateId());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        System.out.println(user);
        if (checkExistingUser(user)){
            throw new UserAlreadyExistsException("This email id is already taken other");
        }
        return userServiceRepository.save(user);

    }

    @Override
    public User updateUserDetails(User userDetails) throws UserNotFoundException {
        Optional<User> userByEmail = userServiceRepository.findUserByEmail(userDetails.getEmail());
        if (userByEmail.isPresent()) {
            User userFromDb = userByEmail.get();
            // Update user details with new values
            userFromDb.setFirstName(userDetails.getFirstName());
            userFromDb.setLastName(userDetails.getLastName());
            userFromDb.setPhoneNumber(userDetails.getPhoneNumber());
            userFromDb.setAddress(userDetails.getAddress());
            userFromDb.setCity(userDetails.getCity());
            userFromDb.setState(userDetails.getState());
            userFromDb.setPinCode(userDetails.getPinCode());
            userFromDb.setGender(userDetails.getGender());
            userFromDb.setImage(userDetails.getImage());
            userFromDb.setProductAddList(userDetails.getProductAddList());

            // Save the updated user to the database
            return userServiceRepository.save(userFromDb);
        }

        throw new UserNotFoundException("No user exists with email id " + userDetails.getEmail());
    }

    @Override
    public User getUserInformation(String emailId) throws UserNotFoundException {
        Optional<User> userByEmail = userServiceRepository.findUserByEmail(emailId);
        if (userByEmail.isPresent()){
            return userByEmail.get();
        }
        throw new UserNotFoundException("User not exists with mail id "+emailId);
    }

    @Override
    public boolean deleteUser(String emailId) throws UserNotFoundException {
        Optional<User> userByEmail = userServiceRepository.findUserByEmail(emailId);
        if (userByEmail.isPresent()){
            String userEmail = userByEmail.get().getEmail();
            userServiceRepository.deleteUserByEmail(userEmail);
            return true;
        }
        throw new UserNotFoundException("User not found with "+emailId);
    }

    @Override
    public User postAnAdd(String emailId, Product product) throws UserNotFoundException {
        Optional<User> userByEmail = userServiceRepository.findUserByEmail(emailId);
        System.out.println(product);
        User user ;
        if (userByEmail.isPresent()){
            user=userByEmail.get();
            List<Product> productAddList = user.getProductAddList();
            LocalDateTime localDate = LocalDateTime.now();
            product.setAddPostedOnDate(localDate);
            if (productAddList==null){
                // create a product list
                //user.setProductAddList(Arrays.asList(product);

                user.setProductAddList(Collections.singletonList(product));

            }else {
                // product list is not empty and add the products to the list
                productAddList.add(product);

                user.setProductAddList(productAddList);
            }
        }else {
            throw new UserNotFoundException("No user found with email id "+emailId);
        }
        userServiceRepository.save(user);
        return user;
    }

    @Override
    public User removeItemsFromProductList(String emailId, Long productId) throws UserNotFoundException, ProductDoesNotExistsException {
        Optional<User> userByEmail = userServiceRepository.findUserByEmail(emailId);
        User user;
        if (userByEmail.isPresent()){
             user = userByEmail.get();
            List<Product> productAddList = user.getProductAddList();
            Optional<Product> optionalProduct = productAddList.stream().filter(searchProduct -> Objects.equals(searchProduct.getId(), productId)).findAny();
            if (optionalProduct.isPresent()){
                productAddList.remove(optionalProduct.get());
                user.setProductAddList(productAddList);
            }else {
                throw new ProductDoesNotExistsException("There is no product with id  "+ productId);
            }

        }else {
            throw new UserNotFoundException("No user with id "+emailId);
        }
        userServiceRepository.save(user);
        return user;
    }
}
