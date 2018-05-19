package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.exception.UserNotFoundException;
import com.codehub.spring.eshop.exception.TokenAccessExpired;
import com.codehub.spring.eshop.repository.AccessTokenRepository;
import com.codehub.spring.eshop.repository.UserRepository;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.TemporalUnit;
import java.util.Date;
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
        User user = userRepository.findByEmail(email);
        //Throws UserNotFound
        if (user== null) throw new UserNotFoundException("User not found");
        if (!user.getPassword().contentEquals(password)) {
            throw new UserNotFoundException("credential mismatch");
        }
        ;
        return accessTokenRepository.save( AccessToken.builder().user(user).accessToken(UUID.randomUUID()).
                createdOn(new Date().toInstant()).expiresIn(new Date().toInstant().plusSeconds(3600)).build());
    }

    @Override
    public void logout(AccessToken token) {
        accessTokenRepository.delete(token);
    }


    @Override
    public User verify(String accessToken) throws TokenAccessExpired {
        AccessToken token =  accessTokenRepository.findByAccessToken(accessToken);
        if (token.getExpiresIn().compareTo(new Date().toInstant())<0) {
            throw new TokenAccessExpired();
        }
        return token.getUser();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
