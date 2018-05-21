package com.codehub.spring.eshop.repository;

import com.codehub.spring.eshop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Dimitris on 16/5/2018.
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    public Collection<Product> findAllByProductCategory(Long categoryId);

    public Collection<Product> findAllByKeywordsIsContaining(String keyword);
}
