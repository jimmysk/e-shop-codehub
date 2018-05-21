package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.CartItem;
import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    public Order saveOrder(Order order);

    public Order updateOrderStatus(int id, OrderStatus orderStatus);

    public Optional<Order> findOrderById(int id);

    public Order checkout(List<CartItem> cartItems);

}
