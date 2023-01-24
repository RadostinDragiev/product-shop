package com.example.productshop.services.impl;

import com.example.productshop.dtos.UserRegisterDto;
import com.example.productshop.entities.User;
import com.example.productshop.repositories.UserRepository;
import com.example.productshop.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper.map(userRegisterDto, User.class);
        this.userRepository.saveAndFlush(user);
    }

    @Override
    public User getRandomUser() {
        Random random = new Random();
        int usersCount = (int) this.userRepository.count();
        int randomId = random.nextInt(usersCount) + 1;
        return this.userRepository.findById((long) randomId).get();
    }
}
