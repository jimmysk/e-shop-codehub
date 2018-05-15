package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public AccessToken login(String username, String password) {
        return null;
    }

    @Override
    public void logout(String accessToken) {

    }

    @Override
    public User verify(String accessToken) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
