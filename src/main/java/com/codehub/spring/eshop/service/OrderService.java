package com.codehub.spring.eshop.service;

import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.enums.OrderStatus;

public interface OrderService {

    public Order saveOrder(Order order);

    public Order updateOrderStatus(int id, OrderStatus orderStatus);

    public Order findOrderById(int id);

}
