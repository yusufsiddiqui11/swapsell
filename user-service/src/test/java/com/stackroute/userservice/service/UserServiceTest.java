package com.stackroute.userservice.service;

import com.stackroute.userservice.configuration.UserDTO;
import com.stackroute.userservice.domain.Product;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.ProductDoesNotExistsException;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.repository.UserServiceRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserServiceRepository userServiceRepository;
    @InjectMocks
    private UserServiceImpl userService;
    private UserDTO userDto;
    List<User> userList;
    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        userList=null;
        userServiceRepository.deleteAll();
    }



//    @Test
//    public void testUpdateUserDetails() throws UserNotFoundException {
//        User user = new User();
//        user.setFirstName("John");
//        user.setLastName("Doe");
//        user.setEmail("johndoe@gmail.com");
//        user.setAddress("123 Main St");
//        user.setImage((byte) 12345);
//        user.setPhoneNumber(1234567890);
//
//        User updatedUser = new User();
//        updatedUser.setEmail("johndoe@gmail.com");
//        updatedUser.setAddress("456 Maple St");
//
//        Mockito.when(userServiceRepository.findUserByEmail("johndoe@gmail.com")).thenReturn(Optional.of(user));
//        Mockito.when(userServiceRepository.save(Mockito.any(User.class))).thenReturn(updatedUser);
//
//        User result = userService.updateUserDetails(updatedUser);
//
//        assertEquals("456 Maple St", result.getAddress());
//    }
//
//    @Test
//    @DisplayName("Test case for deleting user account")
//    public void deleteUserTest() throws UserNotFoundException {
//        String email = "test@example.com";
//        User user = new User();
//        user.setEmail(email);
//        Mockito.when(userServiceRepository.findUserByEmail(email)).thenReturn(Optional.of(user));
//        boolean result = userService.deleteUser(email);
//        assertTrue(result);
//        Mockito.verify(userServiceRepository, Mockito.times(1)).deleteUserByEmail(email);
//    }
//
//    @Test
//    @DisplayName("test to post add for user")
//    public void testPostAnAdd() throws UserNotFoundException {
//        // Create a sample user and product
//
//        Product product = new Product(1L,"laptop","Test Product", "Test Description",(byte) 10.0,500.00,"test category","good",93,true, null);
//
//        User user = new User(1L,"user1","lastName",15748269,"test@example.com","address city",(byte) 10.2, "password",null);
//
//        // Set up the mock UserServiceRepository to return the sample user when searching by email
//        Mockito.when(userServiceRepository.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
//
//        // Call the postAnAdd method with the sample user's email and product
//        User result = userService.postAnAdd(user.getEmail(), product);
//
//        // Verify that the user's product add list contains the new product
//        assertEquals(1, result.getProductAddList().size());
//        assertEquals(product, result.getProductAddList().get(0));
//
//        // Verify that the UserServiceRepository's save method was called with the updated user
//        Mockito.verify(userServiceRepository, Mockito.times(1)).save(result);
//    }
//



}