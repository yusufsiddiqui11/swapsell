package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.configuration.MessageConfiguration;
import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Optional;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() throws UserAlreadyExistException {
        // Arrange
        User user = new User();
        user.setFirstName("Mob");
        user.setLastName("Physcho");
        user.setEmail("mob100@gmail.com");
        user.setPassword("mob100");

        Mockito.when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        Mockito.when(userRepository.save(user)).thenReturn(user);

        // Act
        User savedUser = userService.saveUser(user);

        // Assert
        Mockito.verify(userRepository).findByEmail(user.getEmail());
        Mockito.verify(userRepository).save(user);
        Mockito.verify(rabbitTemplate).convertAndSend(MessageConfiguration.exchangeName1, MessageConfiguration.routingKey1, user);

        Assertions.assertNotNull(savedUser);
        Assertions.assertEquals(user.getFirstName(), savedUser.getFirstName());
        Assertions.assertEquals(user.getLastName(), savedUser.getLastName());
        Assertions.assertEquals(user.getEmail(), savedUser.getEmail());
        Assertions.assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    public void testFindByEmail() {
        // Arrange
        String email = "mob100@gmail.com";
        User user = new User();
        user.setFirstName("Mob");
        user.setLastName("Physcho");
        user.setEmail(email);
        user.setPassword("mob100");

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = userService.findByEmail(email);

        // Assert
        Mockito.verify(userRepository).findByEmail(email);

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(user.getFirstName(), foundUser.get().getFirstName());
        Assertions.assertEquals(user.getLastName(), foundUser.get().getLastName());
        Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
        Assertions.assertEquals(user.getPassword(), foundUser.get().getPassword());
    }

    @Test
    public void testFindByEmailAndPassword() {
        // Arrange
        String email = "mob100@gmail.com";
        String password = "mob100";
        User user = new User();
        user.setFirstName("Mob");
        user.setLastName("Physcho");
        user.setEmail(email);
        user.setPassword(password);

        Mockito.when(userRepository.findByEmailAndPassword(email, password)).thenReturn(Optional.of(user));

        // Act
        Optional<User> foundUser = userService.findByEmailAndPassword(email, password);

        // Assert
        Mockito.verify(userRepository).findByEmailAndPassword(email, password);

        Assertions.assertTrue(foundUser.isPresent());
        Assertions.assertEquals(user.getFirstName(), foundUser.get().getFirstName());
        Assertions.assertEquals(user.getLastName(), foundUser.get().getLastName());
        Assertions.assertEquals(user.getEmail(), foundUser.get().getEmail());
        Assertions.assertEquals(user.getPassword(), foundUser.get().getPassword());
    }

}
