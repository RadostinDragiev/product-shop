package com.example.productshop.repositories;

import com.example.productshop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Set<Product> findProductsByPriceBetweenAndBuyer_IdOrderByPrice(BigDecimal min, BigDecimal max, Long id);
}
