package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.exception.UserNotFoundException;
import com.codehub.spring.eshop.repository.AccessTokenRepository;
import com.codehub.spring.eshop.repository.UserRepository;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Override
    public User register(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(User user) throws UserNotFoundException {
        if (!userRepository.existsById(user.getId())) {
            throw new UserNotFoundException("User not found");
        }
        return userRepository.save(user);
    }


    @Override
    public AccessToken login( String email, String password) throws UserNotFoundException {
        User user ;
        user.findByEmail(email);
        //Throws UserNotFound
        if (user.equals(null)) throw new UserNotFoundException("User not found");
        if (!user.getPassword().contentEquals(password)) {
            throw new UserNotFoundException("credential mismatch");
        }
        return new AccessToken(findByEmail(email), UUID.randomUUID());
    }

    @Override
    public void logout(AccessToken token) {
        accessTokenRepository.delete(token);
    }


    @Override
    public User verify(String accessToken) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findbyEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
