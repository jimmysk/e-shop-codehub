package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "order_items")
@Builder
@Data
public class OrderItem {

    @Id
    @Column(name = "order_item_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;

    @OneToOne(optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "tax")
    private BigDecimal tax;

    @Column(name = "quantity")
    private BigDecimal quantity;

    @Column(name = "date_added")
    private Instant dateAdded;

    @Column(name = "size")
    private Size size;


}
