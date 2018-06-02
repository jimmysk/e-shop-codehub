package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.CartItem;
import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.OrderStatus;
import com.codehub.spring.eshop.exception.EShopException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    public Order saveOrder(Order order);

    public Order updateOrderStatus(int id, OrderStatus orderStatus);

    public Optional<Order> findOrderById(int id) throws EShopException;

    public Order checkout(List<CartItem> cartItems) throws EShopException;

    public Collection<Order> findAllOrdersByUser(User user) throws EShopException;

    public Collection<Order> findByOrderStatusEquals(OrderStatus status) throws EShopException;;

}
