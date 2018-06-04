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
    public User update(User user) throws EShopException {
        if (!userRepository.existsById(user.getId())) {
            throw new UserNotFoundException("User not found");
        }
        User userLoaded = userRepository.findById(user.getId()).get();

        // Check if User change his password
        if (!user.getPassword().equals(userLoaded.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        user.setRole(Role.CUSTOMER);
        return userRepository.save(user);
    }

    @Override
    public User updateUserRole(Long userId, Role role) throws EShopException {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User not found");
        }
        user.get().setRole(role);
        return userRepository.save(user.get());
    }


    @Override
    public AccessToken login(String email, String password) throws EShopException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("Password mismatch");
        }
        authenticate(user, password);

        return accessTokenRepository.save( AccessToken.builder()
                .user(user)
                .accessToken(UUID.randomUUID())
                .createdOn(new Date().toInstant())
                .expiresIn(new Date().toInstant().plusSeconds(3600))
                .build());
    }

    @Override
    public void logout(String accessToken) {
        accessTokenRepository.deleteAccessTokenByAccessToken(UUID.fromString(accessToken));
    }


    @Override
    public User verify(String accessToken) throws EShopException {
        AccessToken token = accessTokenRepository.findByAccessToken(UUID.fromString(accessToken));
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
    public User findByEmail(String email) throws EShopException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("No user found with this email");
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long id) throws EShopException {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new UserNotFoundException("No user found with this Id");
        }
        return user;
    }

    @Override
    public List<User> findUserOrdersGreaterThan(Integer minOrder) throws EShopException{
        return userRepository.findUserOrdersGreaterThan(minOrder);
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
