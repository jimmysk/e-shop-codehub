package com.codehub.spring.eshop.controller;

import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.exception.EShopException;
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
        accessToken = accessToken.replaceAll("^Bearer (.*)", "$1");
        return userService.verify(accessToken);
    }

}
