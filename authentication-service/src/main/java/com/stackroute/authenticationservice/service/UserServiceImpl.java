package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.configuration.MessageConfiguration;
import com.stackroute.authenticationservice.configuration.UserDTO;
import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistException;
import com.stackroute.authenticationservice.repository.UserRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  RabbitTemplate rabbitTemplate;

    @Override
    public User saveUser(User user) throws UserAlreadyExistException {
        if(userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        rabbitTemplate.convertAndSend(MessageConfiguration.exchangeName1, MessageConfiguration.routingKey1, user);
        rabbitTemplate.convertAndSend(MessageConfiguration.exchangeName2, MessageConfiguration.routingKey2, user);

        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

}
