package com.example.productshop.services;

import com.example.productshop.dtos.CategoryRegistrationDto;
import com.example.productshop.entities.Category;

public interface CategoryService {
    void addCategory(CategoryRegistrationDto categoryRegistrationDto);

    Category getRandomCategory();
}
