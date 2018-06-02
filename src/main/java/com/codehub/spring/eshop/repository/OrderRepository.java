package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.domain.Order;
import com.codehub.spring.eshop.domain.User;
import com.codehub.spring.eshop.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    public Collection<Order> findAllByUser(User user);

    public Collection<Order> findByOrderStatusEquals(OrderStatus status);

}
