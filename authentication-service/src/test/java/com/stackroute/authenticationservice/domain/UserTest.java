package com.stackroute.authenticationservice.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUser() {
        // Create a new user
        User user = new User();
        user.setFirstName("Mob");
        user.setLastName("Physcho");
        user.setEmail("mob100@gmail.com");
        user.setPassword("mob100");

        // Verify that the user object was created correctly
        Assertions.assertEquals("Mob", user.getFirstName());
        Assertions.assertEquals("Physcho", user.getLastName());
        Assertions.assertEquals("mob100@gmail.com", user.getEmail());
        Assertions.assertEquals("mob100", user.getPassword());
    }
}