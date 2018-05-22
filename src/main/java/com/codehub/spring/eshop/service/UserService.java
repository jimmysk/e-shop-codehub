package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.exception.EShopException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User register(User user) throws EShopException;

    public AccessToken login(String email, String password) throws EShopException;

    public User update(User user) throws EShopException;

    public void logout(String accessToken);

    public User verify(String accessToken) throws EShopException;

    public List<User> findAll();

    public User findByEmail(String email) throws EShopException;

    public Optional<User> findById(Long id) throws EShopException;

}
