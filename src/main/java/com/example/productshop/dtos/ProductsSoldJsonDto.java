package com.example.productshop.dtos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsSoldJsonDto {
    @Expose
    private int count;

    @Expose
    private List<ProductJsonDto> products;
}
