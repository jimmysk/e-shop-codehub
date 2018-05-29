package com.codehub.spring.eshop.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Dimitris on 15/5/2018.
 */

@RestController
@ApiIgnore
public class HelloController {

    @RequestMapping(value = "/e-shop")
    @ResponseBody
    public String helloWorld() {

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body style='text-align:center;'>\n" +
                "<h1>Hello World!</h1>\n" +
                "<p>Welcome to our full Rest E-shop! </br>\n" +
                "<a href=\"/swagger-ui.html\">Read our OpenAPI 3.0 Documentation </a></p>\n" +
                "</br>\n" +
                "</body>\n" +
                "</html>";
    }


}
