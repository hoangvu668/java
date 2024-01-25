package com.restaurant.restaurant_spring.services.auth.admin;

import com.restaurant.restaurant_spring.dtos.CategoryDto;
import com.restaurant.restaurant_spring.dtos.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminService {
    CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

    List<CategoryDto> getAllCategories();

    List<CategoryDto> getAllCategoriesByTitle(String title);

    ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

    List<ProductDto> getAllProductsByCategory(Long categoryId);

    List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title);

    void deleteProduct(Long productId);

    ProductDto getProductById(Long productId);

    ProductDto updateProductById(Long productId, ProductDto productDto) throws IOException;
}
