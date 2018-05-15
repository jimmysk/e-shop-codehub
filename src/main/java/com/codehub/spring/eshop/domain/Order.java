package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "order_date")
    private Instant orderDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

}
