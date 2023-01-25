package com.example.productshop.dtos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserWithSoldProductsDto {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    Set<ProductsSoldDto> productsSold;
}
