package com.stackroute.slotservice.service;

import com.paypal.api.payments.Payment;
import com.stackroute.slotservice.configuration.MessageConfiguration;
import com.stackroute.slotservice.configuration.UserDTO;
import com.stackroute.slotservice.domain.User;
import com.stackroute.slotservice.repository.UserRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //    @RabbitListener(queues = MessageConfiguration.queueName1)
    public void userDataFromAuthService(UserDTO userDTO) {
        User user = new User();
        String userEmailId = userDTO.getEmail();
        user.setEmailId(userEmailId);
        saveUserData(user);
    }

    @Override
    public User saveUserData(User user) {
        return userRepository.save(user);
    }

    @Override
    public void UpdateUserTransaction(String userId, Payment payment) {
        Optional<User> userById = userRepository.findById(userId);
        User user;
        if (userById.isPresent()){
            System.out.println("user found "+userById);
            user = userById.get();
            List<Payment> paymentList = user.getPaymentList();
            if (paymentList==null){
                user.setPaymentList(Collections.singletonList(payment));
            }else {
                paymentList.add(payment);
                user.setPaymentList(paymentList);
            }
            userRepository.save(user);

        }
    }
}
