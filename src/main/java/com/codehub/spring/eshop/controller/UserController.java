package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.DTO.UserDto;
import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.Role;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.UserNotAuthException;
import com.codehub.spring.eshop.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Dimitris on 15/5/2018.
 */

@RestController
@RequestMapping(value = "/e-shop/user")
@Api(value = "User", description = "Handles the user operations", tags = "User")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;


    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Default request for authenticated user. Requires Access Token ")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<User> getUser(@ApiParam(name = "Authorization", value = "Authorization",
                                                    defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                            @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        return ResponseEntity
                .ok()
                .body(verifyToken(accessToken));
    }

    @GetMapping(value = "/{userId}" , produces = "application/json")
    @ApiOperation(value = "Get User", notes = "Call this endpoint to get user by id")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long userId,
                                            @ApiParam(name = "Authorization", value = "Authorization",
                                                    defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                            @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(userService.findById(userId).get());
    }

    @GetMapping(value = "/email/{userEmail}" , produces = "application/json")
    @ApiOperation(value = "Get User", notes = "Call this endpoint to get user by Email")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<User> getUserByEmail(@PathVariable(name = "userEmail") String userEmail,
                                               @ApiParam(name = "Authorization", value = "Authorization",
                                                       defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                               @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(userService.findByEmail(userEmail));
    }

    @GetMapping(value = "/list" , produces = "application/json")
    @ApiOperation(value = "Get All Users", notes = "Call this endpoint to get all users")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<List<User>> getAllUsers(@ApiParam(name = "Authorization", value = "Authorization",
                                                        defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                                      @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(userService.findAll());
    }

    @PostMapping(produces = "application/json")
    @ApiOperation(value = "Register", notes = "Call this endpoint to register as a new user")
    @ApiResponses({
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@RequestBody User user) throws EShopException {
        return ResponseEntity
                .ok()
                .body(userService.register(user));
    }

    @PutMapping(produces = "application/json")
    @ApiOperation(value = "Update User", notes = "Call this endpoint to update user's fields")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<User> updateUser(@RequestBody User user,
                                           @ApiParam(name = "Authorization", value = "Authorization",
                                                   defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                           @RequestHeader("Authorization") String accessToken) throws EShopException {
        User user1 = verifyToken(accessToken);
        if (!user1.getId().equals(user.getId())) {
            throw new UserNotAuthException("Unable to update user");
        }
        userService.update(user);
        return ResponseEntity
                .ok()
                .body(userService.findById(user.getId()).get());
    }


    @PutMapping(value = "updaterole", produces = "application/json")
    @ApiOperation(value = "Update User's Role", notes = "Call this endpoint to update user's role")
    @ApiResponses({
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<User> updateRole(@RequestParam(value = "user_id") Long userId,
                                           @RequestParam(value = "role") Role role,
                                           @ApiParam(name = "Authorization", value = "Authorization",
                                                   defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                           @RequestHeader("Authorization") String accessToken) throws EShopException {
        isAdminOrFail(verifyToken(accessToken));
        return ResponseEntity
                .ok()
                .body(userService.updateUserRole(userId, role));
    }


    @PostMapping(value = "/login", produces = "application/json")
    @ApiOperation(value = "Login", notes = "Call this endpoint to login as user")
    @ApiResponses({
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<AccessToken> login(@RequestBody UserDto userDto) throws EShopException {
        String email = userDto.getEmail();
        String password = userDto.getPassword();

        return ResponseEntity
                .ok()
                .body(userService.login(email, password));
    }

    @DeleteMapping(value = "/logout", produces = "application/json")
    @ApiOperation(value = "Logout", notes = "Call this endpoint to logout")
    public ResponseEntity<String> logout(@ApiParam(name = "Authorization", value = "Authorization",
                                                defaultValue = "Bearer YOUR_ACCESS_TOKEN_HERE")
                                             @RequestHeader("Authorization") String accessToken)
            throws EShopException {
        verifyToken(accessToken);
        userService.logout(extractAccessToken(accessToken));
        return ResponseEntity
                .ok()
                .body("Hope to see you again!");
    }
}
