package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.User;

import java.math.BigDecimal;

public interface CartService {

    public void addItem(User user, Long productId, BigDecimal quantity);

    public void removeItem(User user, Long productId);

    public BigDecimal increaseQuantity(User user, Long productId, BigDecimal quantity);

    public BigDecimal decreaseQuantity(User user, Long productId, BigDecimal quantity);

}
