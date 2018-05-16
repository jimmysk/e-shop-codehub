package com.codehub.spring.eshop.service.impl;

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
    public void addItem(int productId, BigDecimal quantity) {

    }

    @Override
    public void removeItem(int productId) {

    }

    @Override
    public BigDecimal increaseQuantity(int productId, BigDecimal quantity) {
        return null;
    }

    @Override
    public BigDecimal decreaseQuantity(int productId, BigDecimal quantity) {
        return null;
    }
}
