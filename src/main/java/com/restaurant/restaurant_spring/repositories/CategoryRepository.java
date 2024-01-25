package com.restaurant.restaurant_spring.repositories;

import com.restaurant.restaurant_spring.entities.Category;
import com.restaurant.restaurant_spring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByNameContaining(String title);
}
