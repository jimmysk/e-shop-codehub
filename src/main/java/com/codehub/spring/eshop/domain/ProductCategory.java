package com.codehub.spring.eshop.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "product_categories")
@Data
public class ProductCategory {

    @Id
    @Column(name = "category_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_desc")
    private String categoryDesc;

    @Column(name = "other_category_info")
    private String otherCategoryInfo;

    @OneToMany(mappedBy = "productCategory")
    private Set<Product> products;
}
