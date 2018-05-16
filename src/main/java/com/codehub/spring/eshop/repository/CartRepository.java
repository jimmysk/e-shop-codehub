package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
