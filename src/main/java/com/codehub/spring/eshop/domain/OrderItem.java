package com.codehub.spring.eshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @OneToOne(optional = false)
    @JoinColumn(name = "order_id")
    private int orderId;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id")
    private int productId;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "date_added")
    private Instant dateAdded;
}
