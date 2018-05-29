package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.*;
import com.codehub.spring.eshop.enums.OrderStatus;
import com.codehub.spring.eshop.exception.CartProductNotFoundException;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.exception.OrderNotFoundException;
import com.codehub.spring.eshop.repository.OrderItemRepository;
import com.codehub.spring.eshop.repository.OrderRepository;
import com.codehub.spring.eshop.repository.ProductRepository;
import com.codehub.spring.eshop.service.CartService;
import com.codehub.spring.eshop.service.OrderService;
import com.codehub.spring.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrderStatus(int id, OrderStatus orderStatus) {
        Optional<Order> order = null;
        try {
            order = findOrderById(id);
        } catch (EShopException e) {
            e.printStackTrace();
        }
        order.get().setOrderStatus(orderStatus);
        return orderRepository.save(order.get());
    }

    @Override
    public Optional<Order> findOrderById(int id) throws EShopException {
        Optional<Order> order = orderRepository.findById(id);

        if (!order.isPresent()) {
            throw new OrderNotFoundException();
        }
        return order;
    }

    @Override
    public Order checkout(List<CartItem> cartItems) throws EShopException {

        Order order = Order.builder()
                .user(userService.findById(cartItems.get(1).getUserId()).get())
                .orderDate(new Date().toInstant())
                .amount(getCartAmount(cartItems))
                .tax(cartItems.get(1).getTax())
                .orderStatus(OrderStatus.ORDERED)
                .build();

        // Create order1 to get orderId Back
        Order order1 = saveOrder(order);

        for (CartItem cartItem:cartItems) {

            Optional<Product> product = productRepository.findById(cartItem.getProductId());
            if (!product.isPresent()) {
                throw new CartProductNotFoundException();
            }

            // Decrease Stock of product
            product.get().setStock(product.get().getStock().subtract(cartItem.getQuantity()));
            productRepository.save(product.get());

            OrderItem orderItem = OrderItem.builder()
                    .order(order1)
                    .product(product.get())
                    .price(cartItem.getPrice())
                    .tax(cartItem.getTax())
                    .quantity(cartItem.getQuantity())
                    .dateAdded(order1.getOrderDate())
                    .size(cartItem.getSize())
                    .build();

            orderItemRepository.save(orderItem);
        }

        // Delete Cart After Create Order
        try {
            cartService.dropCart(order1.getUser().getId());
        } catch (EShopException e) {
            e.printStackTrace();
        }

        return order1;
    }

    @Override
    public Collection<Order> findAllOrdersByUser(User user) throws EShopException {
        Collection<Order> orders = orderRepository.findAllByUser(user);
        if (orders.isEmpty()) {
            throw new OrderNotFoundException();
        }
        return orders;
    }

    //Calculate Amount of Order by CartList
    private BigDecimal getCartAmount(List<CartItem> cartItems) {
        BigDecimal amount = new BigDecimal("0");
        for (CartItem cartItem:cartItems) {
            amount = amount.add(cartItem.getPrice());
        }
        return amount;
    }
}
