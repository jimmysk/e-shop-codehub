package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.OrderStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "orders")
@Data
public class Order {

    private int id;

    private int userId;

    private Instant orderDate;

    private BigDecimal amount;

    private BigDecimal tax;

    private OrderStatus orderStatus;
}
