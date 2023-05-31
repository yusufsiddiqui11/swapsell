package com.stackroute.slotservice.controller;

import com.stackroute.slotservice.domain.User;
import com.stackroute.slotservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @PostMapping("/registerUserPayment")
//    public ResponseEntity<?> registerUser(@RequestBody User user){
//        User saveUserData = userService.saveUserData(user);
//        return new ResponseEntity<>(saveUserData, HttpStatus.OK);
//    }
}

