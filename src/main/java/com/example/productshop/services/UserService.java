package com.example.productshop.services;

import com.example.productshop.dtos.UserRegisterDto;
import com.example.productshop.dtos.UserWithSoldProductsDto;
import com.example.productshop.entities.User;

import java.util.Set;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    User getRandomUser();

    Set<UserWithSoldProductsDto> getAllUsersWithSoldProducts();
}
