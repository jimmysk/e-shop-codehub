package com.codehub.spring.eshop.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Dimitris on 14/5/2018.
 */

@Entity
@Table(name = "product_categories")
@Data
public class ProductCategory {

    private int id;

    private String categoryDesc;

    private String otherCategoryInfo;
}
