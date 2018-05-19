package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User register(User user);

    public AccessToken login( String email, String password)throws UserNotFoundException;

    public User update(User user) throws UserNotFoundException;

    public void logout(AccessToken token);

    public User verify(String accessToken) throws EShopException;

    public List<User> findAll();

    public User findByEmail(String email);

    public Optional<User> findById(Long id);

}
