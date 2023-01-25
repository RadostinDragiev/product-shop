package com.example.productshop.dtos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryByProductsDto {
    @Expose
    private String category;

    @Expose
    private int productCount;

    @Expose
    private double averagePrice;

    @Expose
    private double totalRevenue;
}
