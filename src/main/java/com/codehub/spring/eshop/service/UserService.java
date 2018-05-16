package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;

import java.util.List;

public interface UserService {

    public User save(User user);

    public AccessToken login(String username, String password);

    public User update(String userId, User user);

    public void logout(String accessToken);

    public User verify(String accessToken);

    public List<User> findAll();

    public User findByEmail(String email);

    public User findById(String id);

}
