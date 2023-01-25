package com.example.productshop.services;

import com.example.productshop.dtos.ProductInRangeDto;
import com.example.productshop.dtos.ProductRegistrationDto;
import com.example.productshop.entities.Product;

import java.util.Set;

public interface ProductService {
    void addProduct(ProductRegistrationDto productRegistrationDto);

    Set<ProductInRangeDto> getAllProductsInRange(double min, double max);
}
