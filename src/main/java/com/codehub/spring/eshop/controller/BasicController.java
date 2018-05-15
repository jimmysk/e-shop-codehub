package com.codehub.spring.eshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Dimitris on 15/5/2018.
 */

@RestController
@RequestMapping(value = "/e-shop")
public class BasicController {

    @RequestMapping(value = "hello")
    @ResponseBody
    public String helloWorld() {
        return "HelloWorld!!!";
    }
}
