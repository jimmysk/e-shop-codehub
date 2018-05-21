package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.AccessToken;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.Role;
import com.codehub.spring.eshop.exception.*;
import com.codehub.spring.eshop.repository.AccessTokenRepository;
import com.codehub.spring.eshop.repository.UserRepository;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccessTokenRepository accessTokenRepository;

    @Override
    public User register(User user) throws EmailExistsException {

        if (emailExist(user.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + user.getEmail()
            );
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
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
        if (user == null) {
            throw new UserNotFoundException("Password mismatch");
        }
        try {
            authenticate(user, password);
        } catch (UserNotAuthException e) {
            e.printStackTrace();
        }
        //Throws UserNotFound
        if (user== null) throw new UserNotFoundException("User not found");
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException("Password mismatch");
        }

        return accessTokenRepository.save( AccessToken.builder()
                .user(user)
                .accessToken(UUID.randomUUID())
                .createdOn(new Date().toInstant())
                .expiresIn(new Date().toInstant().plusSeconds(3600))
                .build());
    }

    @Override
    public void logout(AccessToken token) {
        accessTokenRepository.delete(token);
    }


    @Override
    public User verify(String accessToken) throws EShopException {
        AccessToken token =  accessTokenRepository.findByAccessToken(accessToken);
        if (token == null) {
            throw new TokenNotFoundException();
        }
        if (token.getExpiresIn().compareTo(new Date().toInstant())<0) {
            throw new TokenAccessExpiredException();
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


    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }

    private User authenticate(User user, String plainTextPassword) throws UserNotAuthException {
        return validate(user, plainTextPassword);
    }

    private User validate(User user, String plainTextPassword) throws UserNotAuthException {
        if (plainTextPassword == null || !passwordEncoder.matches(plainTextPassword, user.getPassword())) {
            throw new UserNotAuthException("This user is NOT authenticated");
        }
        return user;
    }
}
