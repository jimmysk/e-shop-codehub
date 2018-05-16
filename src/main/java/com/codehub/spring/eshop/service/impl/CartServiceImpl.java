package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.repository.CartRepository;
import com.codehub.spring.eshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void addItem(User user, Long productId, BigDecimal quantity) {

    }

    @Override
    public void removeItem(User user, Long productId) {

    }

    @Override
    public BigDecimal increaseQuantity(User user, Long productId, BigDecimal quantity) {
        return null;
    }

    @Override
    public BigDecimal decreaseQuantity(User user, Long productId, BigDecimal quantity) {
        return null;
    }
}
