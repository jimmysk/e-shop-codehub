package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.CartItem;
import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.enums.Size;
import com.codehub.spring.eshop.exception.CartNotFoundException;
import com.codehub.spring.eshop.exception.CartProductNotFoundException;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.repository.CartItemRepository;
import com.codehub.spring.eshop.repository.ProductRepository;
import com.codehub.spring.eshop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void addItem(Long userId, Long productId, BigDecimal quantity, Size size) throws CartProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            CartItem cart = CartItem.builder()
                    .userId(userId)
                    .productId(productId)
                    .quantity(quantity)
                    .price(product.get().getPrice())
                    .tax(product.get().getTax())
                    .dateAdded(new Date().toInstant())
                    .size(size)
                    .build();

            Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
            cartItem.ifPresent(cartItem1 -> cart.setCartId(cartItem1.getCartId()));
            cartItemRepository.save(cart);
        } else {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void removeItem(Long userId, Long productId) throws EShopException {
        if (cartItemRepository.removeCartItemByUserIdAndProductId(userId, productId) < 1) {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void updateQuantity(Long userId, Long productId, BigDecimal quantity) throws EShopException {
        Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem.isPresent()) {
            cartItem.get().setQuantity(quantity);
            cartItemRepository.save(cartItem.get());
        } else {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void updateSize(Long userId, Long productId, Size size) throws EShopException {
        Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);
        if (cartItem.isPresent()) {
            cartItem.get().setSize(size);
            cartItemRepository.save(cartItem.get());
        } else {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void dropCart(Long userId) throws EShopException {
        if (cartItemRepository.deleteAllByUserId(userId) < 1) {
            throw new CartNotFoundException();
        }
    }

    @Override
    public Collection<CartItem> findAll(Long userId) {
        return cartItemRepository.findAllByUserId(userId);
    }
}
