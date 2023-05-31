package com.stackroute.userservice.repository;

import com.stackroute.userservice.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import org.assertj.core.api.Assertions;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
@ExtendWith(SpringExtension.class)
@DataMongoTest
class UserServiceRepositoryTest {

    @Autowired
    private UserServiceRepository userServiceRepository;
    private User user;
    private List<User> userList;
//    @BeforeEach
//    void setUp() {
//        user = new User(1L,"user1","lname1",987456321,"user1@gmail.com","address1", (byte) 125,"user1",null);
//    }

//    @AfterEach
//    void tearDown() {
//        user=null;
//        userServiceRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName("Test case for finding user name")
//    void findUserByEmail() {
//        userServiceRepository.save(user);
//        User user1 = userServiceRepository.findUserByEmail(user.getEmail()).get();
//        assertNotNull(user1);
//        assertEquals(user.getEmail(),user1.getEmail());
//    }
//
//    @Test
//    @DisplayName("Save user to data base")
//    public void saveUserToDataBase(){
//        userServiceRepository.save(user);
//        assertThat(user.getEmail()).isEqualTo("user1@gmail.com");
//    }
    
}