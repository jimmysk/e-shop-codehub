package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.Role;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.UserNotAuthException;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dimitris on 17/5/2018.
 */

@RestController
abstract class BaseController {

    @Autowired
    private UserService userService;


    User verifyToken(String accessToken) throws EShopException {
        accessToken = extractAccessToken(accessToken);
        return userService.verify(accessToken);
    }

    String extractAccessToken(String accessToken) {
        return accessToken.replaceAll("^Bearer (.*)", "$1");
    }

    void isAdminOrFail(User user) throws UserNotAuthException {
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new UserNotAuthException("You are not authorized to make this action");
        }

    }
}
