package com.example.productshop.repositories;

import com.example.productshop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT c.name, COUNT(pc.products_id), AVG(p.price), SUM(p.price) FROM category AS c LEFT JOIN products_categories pc on c.id = pc.categories_id LEFT JOIN products p on p.id = pc.products_id GROUP BY c.id, c.name;", nativeQuery = true)
    Set<String> getAllCategoryCountAndTheirRevenueInformation();
}
