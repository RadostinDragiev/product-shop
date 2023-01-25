package com.example.productshop.dtos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsersProductsJsonDto {
    @Expose
    private int userCount;

    @Expose
    private List<UsersJsonDto> users;
}
