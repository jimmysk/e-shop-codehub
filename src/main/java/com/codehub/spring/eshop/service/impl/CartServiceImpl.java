package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.service.CartService;

import java.math.BigDecimal;

public class CartServiceImpl implements CartService {
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
