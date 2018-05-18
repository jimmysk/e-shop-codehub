package com.codehub.spring.eshop.request.interceptor;

import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String accessToken = request.getHeader("Authorization");
        if (accessToken != null) {
            accessToken = accessToken.replaceAll("^Bearer (.*)", "$1");
            User user = userService.verify(accessToken);
            response.addHeader("x-user-email", user.getEmail());
            response.addHeader("x-first-name", user.getFirstName());
            response.addHeader("x-last-name", user.getLastName());
            response.addHeader("x-user-id", user.getId().toString());
            response.addHeader("x-user-role", user.getRole().name());
        } else {
            response.addHeader("x-user-email", "Anonymous");
        }
        return true;
    }

}

