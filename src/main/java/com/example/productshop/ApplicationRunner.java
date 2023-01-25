package com.example.productshop;

import com.example.productshop.dtos.*;
import com.example.productshop.services.CategoryService;
import com.example.productshop.services.ProductService;
import com.example.productshop.services.UserService;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

@Controller
public class ApplicationRunner implements CommandLineRunner {
    private final Gson gson;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public ApplicationRunner(Gson gson, UserService userService, CategoryService categoryService, ProductService productService) {
        this.gson = gson;
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Seed data from files to DB
        //fetchUsers();
        //fetchCategories();
        //fetchProducts();

        //createFileWithAllProductsInRange();

        //createFileWithUsersSoldProducts();

        createFileWithCategoriesByCount();
    }

    private void createFileWithCategoriesByCount() {
        try {
            Set<CategoryByProductsDto> allCategorySummary = this.categoryService.getAllCategorySummary();
            FileWriter fileWriter = new FileWriter("src/main/resources/outputs/categories-by-products.json.json");
            this.gson.toJson(allCategorySummary, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Found exception " + e.getMessage());
        }
    }

    private void createFileWithUsersSoldProducts() {
        try {
            Set<UserWithSoldProductsDto> users = this.userService.getAllUsersWithSoldProducts();
            FileWriter fileWriter = new FileWriter("src/main/resources/outputs/users-sold-products.json");
            this.gson.toJson(users, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Found exception " + e.getMessage());
        }
    }

    private void createFileWithAllProductsInRange() {
        try {
            Set<ProductInRangeDto> allProductsInRange = this.productService.getAllProductsInRange(500, 1000);
            FileWriter fileWriter = new FileWriter("src/main/resources/outputs/product-in-range.json");
            this.gson.toJson(allProductsInRange, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Found exception " + e.getMessage());
        }
    }

    private void fetchProducts() throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader("src/main/resources/files/products.json"));

        ProductJsonDto[] productJsonDto = this.gson.fromJson(jsonReader, ProductJsonDto[].class);
        Arrays.stream(productJsonDto).forEach(product -> this.productService.addProduct(new ProductRegistrationDto(product.getName(), product.getPrice())));
    }

    private void fetchCategories() throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader("src/main/resources/files/categories.json"));

        CategoriesJsonDto[] categoriesJsonDto = this.gson.fromJson(jsonReader, CategoriesJsonDto[].class);

        Arrays.stream(categoriesJsonDto).forEach(category -> this.categoryService.addCategory(new CategoryRegistrationDto(category.getName())));
    }

    private void fetchUsers() throws FileNotFoundException {
        JsonReader jsonReader = new JsonReader(new FileReader("src/main/resources/files/users.json"));

        UserJsonDto[] userJsonDto = this.gson.fromJson(jsonReader, UserJsonDto[].class);

        Arrays.stream(userJsonDto).forEach(user ->
                this.userService.registerUser(new UserRegisterDto(user.getFirstName(), user.getLastName(), user.getAge()))
        );
    }
}
