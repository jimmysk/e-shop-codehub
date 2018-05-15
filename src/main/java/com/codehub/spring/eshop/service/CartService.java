package com.codehub.spring.eshop.service;

import java.math.BigDecimal;

public interface CartService {

    public void addItem(int productId, BigDecimal quantity);

    public void removeItem(int productId);

    public BigDecimal increaseQuantity(int productId, BigDecimal quantity);

    public BigDecimal decreaseQuantity(int productId, BigDecimal quantity);

}
