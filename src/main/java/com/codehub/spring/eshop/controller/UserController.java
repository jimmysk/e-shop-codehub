package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
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
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") String userId) {
        return ResponseEntity
                .ok()
                .body(userService.findById (userId));
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
        userService.save(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(user));
    }

    @PutMapping(value = "user/update" , consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        userService.update(user);
        return ResponseEntity
                .ok()
                .body(userService.findById (user.getId().toString()));
    }

    @PostMapping(value = "user/login")
    public ResponseEntity<AccessToken> login(@RequestBody String username, String password) {
        return ResponseEntity
                .ok()
                .body(userService.login(username,password));
    }

    @PostMapping(value = "user/logout")
    public ResponseEntity<String> logout(String token) {
        userService.logout(token);
        return ResponseEntity
                .ok()
                .body("Hope to see you again!");
    }



}
