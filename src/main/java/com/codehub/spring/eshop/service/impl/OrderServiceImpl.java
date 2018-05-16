package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.enums.OrderStatus;
import com.codehub.spring.eshop.repository.OrderRepository;
import com.codehub.spring.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return null;
    }

    @Override
    public Order updateOrderStatus(int id, OrderStatus orderStatus) {
        return null;
    }

    @Override
    public Order findOrderById(int id) {
        return null;
    }
}
