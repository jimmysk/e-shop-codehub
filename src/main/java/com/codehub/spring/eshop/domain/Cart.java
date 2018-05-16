package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.Size;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @Column(name = "cart_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany
    private Set<Product> products;

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
