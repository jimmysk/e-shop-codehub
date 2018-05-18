package com.codehub.spring.eshop.service.impl;

import com.codehub.spring.eshop.domain.Cart;
import com.codehub.spring.eshop.domain.Product;
import com.codehub.spring.eshop.enums.Size;
import com.codehub.spring.eshop.exception.CartNotFoundException;
import com.codehub.spring.eshop.exception.CartProductNotFoundException;
import com.codehub.spring.eshop.exception.EShopException;
import com.codehub.spring.eshop.repository.CartRepository;
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
    CartRepository cartRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void addItem(Long userId, Long productId, BigDecimal quantity, Size size) throws CartProductNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isPresent()) {
            Cart cart = Cart.builder()
                    .userId(userId)
                    .productId(productId)
                    .quantity(quantity)
                    .price(product.get().getPrice())
                    .tax(product.get().getTax())
                    .dateAdded(new Date().toInstant())
                    .size(size)
                    .build();

            Optional<Cart> cartItem = cartRepository.findByUserIdAndProductId(userId, productId);
            cartItem.ifPresent(cart1 -> cart.setCartId(cart1.getCartId()));
            cartRepository.save(cart);
        } else {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void removeItem(Long userId, Long productId) throws EShopException {
        if (cartRepository.removeCartByUserIdAndProductId(userId, productId) < 1) {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void updateQuantity(Long userId, Long productId, BigDecimal quantity) throws EShopException {
        if (cartRepository.updateQuantity(userId, productId, quantity) < 1) {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void updateSize(Long userId, Long productId, Size size) throws EShopException {
        if (cartRepository.updateSize(userId, productId, size.name()) < 1) {
            throw new CartProductNotFoundException();
        }
    }

    @Override
    public void dropCart(Long userId) throws EShopException {
        if (cartRepository.deleteAllByUserId(userId) < 1) {
            throw new CartNotFoundException();
        }
    }

    @Override
    public Collection<Cart> findAll(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }
}
