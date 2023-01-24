package com.example.productshop.services.impl;

import com.example.productshop.dtos.ProductRegistrationDto;
import com.example.productshop.entities.Category;
import com.example.productshop.entities.Product;
import com.example.productshop.repositories.ProductRepository;
import com.example.productshop.services.CategoryService;
import com.example.productshop.services.ProductService;
import com.example.productshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public void addProduct(ProductRegistrationDto productRegistrationDto) {
        Product product = this.modelMapper.map(productRegistrationDto, Product.class);
        product.setBuyer(this.userService.getRandomUser());
        product.setSeller(this.userService.getRandomUser());
        product.setCategories(getCategories());

        this.productRepository.saveAndFlush(product);
    }

    private Set<Category> getCategories() {
        Set<Category> categories = new HashSet<>();
        int categoryCount = new Random().nextInt(3) + 1;
        for (int i = 0; i < categoryCount; i++) {
            categories.add(this.categoryService.getRandomCategory());
        }
        return categories;
    }
}
