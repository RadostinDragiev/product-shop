package com.example.productshop.services.impl;

import com.example.productshop.dtos.CategoryByProductsJsonDto;
import com.example.productshop.dtos.CategoryRegistrationDto;
import com.example.productshop.entities.Category;
import com.example.productshop.repositories.CategoryRepository;
import com.example.productshop.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addCategory(CategoryRegistrationDto categoryRegistrationDto) {
        Category category =  this.modelMapper.map(categoryRegistrationDto, Category.class);
        this.categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category getRandomCategory() {
        int randomId = new Random().nextInt((int)this.categoryRepository.count()) + 1;
        return this.categoryRepository.findById((long) randomId).get();
    }

    @Override
    public Set<CategoryByProductsJsonDto> getAllCategorySummary() {
        Set<CategoryByProductsJsonDto> categoryByProductsDtos = new HashSet<>();
        this.categoryRepository.getAllCategoryCountAndTheirRevenueInformation()
                        .forEach(category -> {
                            String[] categorySplit = category.split(",");
                            categoryByProductsDtos.add(new CategoryByProductsJsonDto(categorySplit[0],
                                    Integer.parseInt(categorySplit[1]),
                                    Double.parseDouble(categorySplit[2]),
                                    Double.parseDouble(categorySplit[3])));
                        });
        return categoryByProductsDtos;
    }
}
