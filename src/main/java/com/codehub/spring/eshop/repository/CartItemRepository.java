package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public int removeCartItemByUserIdAndProductId(Long userId, Long productId);

    public int deleteAllByUserId(Long userId);

    public Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

    public Collection<CartItem> findAllByUserId(Long userId);
}
