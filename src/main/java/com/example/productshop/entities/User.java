package com.example.productshop.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private int age;

    @OneToMany(mappedBy = "seller")
    private Set<Product> productsSold;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Set<Product> productsBought;

    @OneToMany
    private Set<User> friends;
}
