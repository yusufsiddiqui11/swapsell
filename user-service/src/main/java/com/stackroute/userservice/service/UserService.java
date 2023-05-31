package com.stackroute.userservice.service;

import com.stackroute.userservice.configuration.UserDTO;
import com.stackroute.userservice.domain.Product;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.ProductDoesNotExistsException;
import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;

public interface UserService {
    User registerUserToApplication(UserDTO userDTO) throws UserAlreadyExistsException;
    public User updateUserDetails(User userDetails) throws UserNotFoundException;
    User getUserInformation(String emailId) throws UserNotFoundException;
    boolean deleteUser(String emailId) throws UserNotFoundException;
    User postAnAdd(String emailId, Product product) throws UserNotFoundException;
    User removeItemsFromProductList(String emailId,Long productId) throws UserNotFoundException, ProductDoesNotExistsException;

}
