package com.codehub.spring.eshop.DTO;

import lombok.Data;

/**
 * Created by Dimitris on 21/5/2018.
 */

@Data
public class SalesByCategoryDto {

    private String category_desc;

    private String product_desc;

    private Integer total_orders;
}
