package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.enums.OrderStatus;
import com.codehub.spring.eshop.repository.OrderRepository;
import com.codehub.spring.eshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(int id, OrderStatus orderStatus) {
        Optional<Order> order = findOrderById(id);
        order.get().setOrderStatus(orderStatus);
        return orderRepository.save(order.get());
    }

    @Override
    public Optional<Order> findOrderById(int id) {
        return orderRepository.findById(id);
    }
}
