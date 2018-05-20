package com.codehub.spring.eshop.domain;

import com.codehub.spring.eshop.enums.Size;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "cart")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class CartItem {

    @Id
    @Column(name = "cart_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "product_id")
    private Long productId;

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

//    @OneToOne(optional = false)
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;

//    @OneToMany
//    private Set<Product> products;

}
