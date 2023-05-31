package com.stackroute.userservice.controller;

import com.stackroute.userservice.domain.Product;
import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.ProductDoesNotExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/swapSell")
public class UserServiceController {
    private final UserService userService;
    @Autowired
    public UserServiceController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/registerUser")
//    public ResponseEntity<?> registerUser(@RequestBody Us user){
//        try {
//            User userDetails = userService.registerUserToApplication(user);
//            return new ResponseEntity<>(userDetails,HttpStatus.CREATED);
//        } catch (UserAlreadyExistsException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }

    @GetMapping("/user/{email}")
    public ResponseEntity<?> getUser(@PathVariable("email") String email) {
        try {
            User userInformation = userService.getUserInformation(email);
            return new ResponseEntity<>(userInformation,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);

        }
    }

    @GetMapping("/getUsersData")
    public ResponseEntity<?> getUserData(HttpServletRequest httpServletRequest){
        String emailId = httpServletRequest.getAttribute("emailId").toString();
        try {
            User userInformation = userService.getUserInformation(emailId);
            return new ResponseEntity<>(userInformation,HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/user/update")
    public ResponseEntity<?> updateUserDetails(@RequestBody User user) {
        try {
            User updateUserDetails = userService.updateUserDetails(user);
            System.out.println("Data is saved");
            return new ResponseEntity<>(updateUserDetails, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/deleteUser")
    public ResponseEntity<?> deleteUser(HttpServletRequest httpServletRequest ){
        try {
            String emailId = httpServletRequest.getAttribute("emailId").toString();
            userService.deleteUser(emailId);
            return new ResponseEntity<>("User with "+emailId +" removed from database",HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/user/productAdd")
    public ResponseEntity<?> PostUserProductAdd(HttpServletRequest httpServletRequest , @RequestBody  Product product){
        try {
            String emailId = httpServletRequest.getAttribute("emailId").toString();
            User user = userService.postAnAdd(emailId, product);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);

        }
    }

    @DeleteMapping("/user/deleteProduct/{productID}")
    public ResponseEntity<?> deleteProductId(HttpServletRequest httpServletRequest, @PathVariable Long productID){
        try {
            String emailId = httpServletRequest.getAttribute("emailId").toString();
            User user = userService.removeItemsFromProductList(emailId, productID);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } catch (UserNotFoundException | ProductDoesNotExistsException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }
}
