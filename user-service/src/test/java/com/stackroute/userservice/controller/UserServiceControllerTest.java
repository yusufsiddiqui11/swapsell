package com.stackroute.userservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.domain.Product;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.service.UserServiceImpl;
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
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static reactor.core.publisher.Mono.when;

@ExtendWith(MockitoExtension.class)
class UserServiceControllerTest {
    @Mock
    UserServiceImpl userService;
    @InjectMocks
    UserServiceController userServiceController;





    MockMvc mockMvc;
    User user;
    List<User> userList;



    @AfterEach
    void tearDown() {
        user=null;
        userList=null;
    }
    private String jsontoString(Object ob)  {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsoncontent = mapper.writeValueAsString(ob);//this will call the toString() of Customer Class
            return jsoncontent;
        }
        catch (JsonProcessingException exception)
        {
            return exception.getMessage();
        }
    }



//    @Test
//    public void testUpdateUserDetails_Success() throws UserNotFoundException {
//        // Arrange
//        User user = new User();
//        user.setEmail("test@test.com");
//        user.setFirstName("John");
//        user.setLastName("Doe");
//        user.setPhoneNumber(1234567890);
//        user.setAddress("123 Main St");
//
//        Mockito.when(userService.updateUserDetails(user)).thenReturn(user);
//
//        // Act
//        ResponseEntity<?> response = userServiceController.updateUserDetails(user);
//
//        // Assert
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(user, response.getBody());
//    }

//    @Test
//    public void testUpdateUserDetails_UserNotFoundException() throws UserNotFoundException {
//        // Arrange
//        User user = new User();
//        user.setEmail("test@test.com");
//
////        Mockito.when(userService.updateUserDetails(user)).thenThrow(new UserNotFoundException("No user found with email id test@test.com"));
////
////        // Act
////        ResponseEntity<?> response = userServiceController.updateUserDetails(user);
////
////        // Assert
//        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
//        assertEquals("No user found with email id test@test.com", response.getBody());
//    }


}