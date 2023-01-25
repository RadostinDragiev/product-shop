package com.example.productshop.services.impl;

import com.example.productshop.dtos.ProductInRangeDto;
import com.example.productshop.dtos.ProductRegistrationDto;
import com.example.productshop.entities.Category;
import com.example.productshop.entities.Product;
import com.example.productshop.entities.User;
import com.example.productshop.repositories.ProductRepository;
import com.example.productshop.services.CategoryService;
import com.example.productshop.services.ProductService;
import com.example.productshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
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
        User buyer = this.userService.getRandomUser();
        if (buyer.getId() > 10) {
            product.setBuyer(this.userService.getRandomUser());
        } else {
            product.setSeller(null);
        }
        product.setSeller(this.userService.getRandomUser());
        product.setCategories(getCategories());

        this.productRepository.saveAndFlush(product);
    }

    @Override
    public Set<ProductInRangeDto> getAllProductsInRange(double min, double max) {
        Set<Product> productsByPriceBetweenAndBuyerIdOrderByPrice = this.productRepository.findProductsByPriceBetweenAndBuyer_IdOrderByPrice(new BigDecimal(min), new BigDecimal(max), null);
        this.modelMapper.createTypeMap(Product.class, ProductInRangeDto.class)
                .addMappings(new PropertyMap<Product, ProductInRangeDto>() {
            @Override
            protected void configure() {
                using(ctx -> ((Product) ctx.getSource()).getSeller().getFirstName() + " " + ((Product) ctx.getSource()).getSeller().getLastName())
                        .map(source, destination.getSeller());
            }
        });

        Set<ProductInRangeDto> productInRangeDtos = new HashSet<>();
        productsByPriceBetweenAndBuyerIdOrderByPrice.forEach(product -> productInRangeDtos.add(this.modelMapper.map(product, ProductInRangeDto.class)));
        return productInRangeDtos;
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
