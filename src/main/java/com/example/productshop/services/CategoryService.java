package com.example.productshop.services;

import com.example.productshop.dtos.CategoryByProductsJsonDto;
import com.example.productshop.dtos.CategoryRegistrationDto;
import com.example.productshop.entities.Category;

import java.util.Set;

public interface CategoryService {
    void addCategory(CategoryRegistrationDto categoryRegistrationDto);

    Category getRandomCategory();

    Set<CategoryByProductsJsonDto> getAllCategorySummary();
}
