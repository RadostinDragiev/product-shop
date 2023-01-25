package com.example.productshop.dtos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductInRangeDto implements Serializable {
    @Expose
    private String name;

    @Expose
    private BigDecimal price;

    @Expose
    private String seller;
}
