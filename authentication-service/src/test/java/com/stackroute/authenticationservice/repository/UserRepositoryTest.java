package com.stackroute.authenticationservice.repository;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        user.setFirstName("Mob");
        user.setLastName("Physcho");
        user.setEmail("mob100@gmail.com");
        user.setPassword("mob100");
    }

    @Test
    public void testFindByEmail() {
        String email = "mob100@gmail.com";
        String password = "mob100";
        String firstName = "Mob";
        String lastName = "Physcho";

        Optional<User> optionalUser = userRepository.findByEmail(email);
        Assertions.assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(firstName, user.getFirstName());
        Assertions.assertEquals(lastName, user.getLastName());
    }

    @Test
    public void testFindByEmailAndPassword() {
        String email = "mob100@gmail.com";
        String password = "mob100";
        String firstName = "Mob";
        String lastName = "Physcho";

        Optional<User> optionalUser = userRepository.findByEmailAndPassword(email, password);
        Assertions.assertTrue(optionalUser.isPresent());

        User user = optionalUser.get();
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertEquals(firstName, user.getFirstName());
        Assertions.assertEquals(lastName, user.getLastName());
    }

}
