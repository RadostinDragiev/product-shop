package com.example.productshop.services;

import com.example.productshop.dtos.UserRegisterDto;
import com.example.productshop.entities.User;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    User getRandomUser();
}
