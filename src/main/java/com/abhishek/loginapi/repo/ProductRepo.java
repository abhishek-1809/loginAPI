package com.abhishek.loginapi.repo;

import com.abhishek.loginapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2 ORDER BY p.price ASC LIMIT ?3")
    List<Product> findTopByPriceRange(int minPrice, int maxPrice, int top);
}
