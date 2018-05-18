package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codehub.spring.eshop.service.UserService;

import java.util.List;

/**
 * Created by Dimitris on 15/5/2018.
 */

@RestController
@RequestMapping(value = "/e-shop")
public class UserController {

    @Autowired
    private UserService userService;



    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long userId) {
        return ResponseEntity
                .ok()
                .body(userService.findById(userId));
    }

    @GetMapping(value = "/user/{userEmail}")
    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "userEmail") String userEmail) {
        return ResponseEntity
                .ok()
                .body(userService.findByEmail (userEmail));
    }

    @GetMapping(value = "user/listusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity
                .ok()
                .body(userService.findAll());
    }

    @PostMapping(value = "user/new", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.register (user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(user));
    }

    @PutMapping(value = "user/update" , consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user)throws UserNotFoundException {
        userService.update(user);
        return ResponseEntity
                .ok()
                .body(userService.findById (user.getId().toString()));
    }

    @PostMapping(value = "user/login")
    public ResponseEntity<AccessToken> login(String email,String password) throws UserNotFoundException {
        return ResponseEntity
                .ok()
                .body(userService.login(email,password));
    }

    @PostMapping(value = "user/logout")
    public ResponseEntity<String> logout(AccessToken token) {
        userService.logout(token);
        return ResponseEntity
                .ok()
                .body("Hope to see you again!");
    }



}
