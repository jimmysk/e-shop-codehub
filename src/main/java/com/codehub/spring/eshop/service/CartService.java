package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.exception.CartProductNotFoundException;
import com.codehub.spring.eshop.exception.EShopException;

import java.math.BigDecimal;

public interface CartService {

    public void addItem(Long userId, Long productId, BigDecimal quantity) throws CartProductNotFoundException;

    public void removeItem(Long userId, Long productId) throws EShopException;

    public void increaseQuantity(Long userId, Long productId, BigDecimal quantity) throws EShopException;

    public void decreaseQuantity(Long userId, Long productId, BigDecimal quantity) throws EShopException;

}
