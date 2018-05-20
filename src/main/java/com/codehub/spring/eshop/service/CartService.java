package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.CartItem;
import com.codehub.spring.eshop.enums.Size;
import com.codehub.spring.eshop.exception.CartProductNotFoundException;
import com.codehub.spring.eshop.exception.EShopException;

import java.math.BigDecimal;
import java.util.Collection;

public interface CartService {

    public void addItem(Long userId, Long productId, BigDecimal quantity, Size size) throws CartProductNotFoundException;

    public void removeItem(Long userId, Long productId) throws EShopException;

    public void updateQuantity(Long userId, Long productId, BigDecimal quantity) throws EShopException;

    public void updateSize(Long userId, Long productId, Size size) throws EShopException;

    public void dropCart(Long userId) throws EShopException;

    public Collection<CartItem> findAll(Long userId);

}
