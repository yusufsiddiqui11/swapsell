package com.stackroute.authenticationservice.service;

import com.stackroute.authenticationservice.domain.User;
import com.stackroute.authenticationservice.exception.UserAlreadyExistException;

import java.util.Optional;

public interface UserService {
    User saveUser(User user) throws UserAlreadyExistException;
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
