package com.restaurant.restaurant_spring.controllers;

import com.restaurant.restaurant_spring.dtos.CategoryDto;
import com.restaurant.restaurant_spring.dtos.ProductDto;
import com.restaurant.restaurant_spring.services.auth.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    @PostMapping("/category")
    public ResponseEntity<CategoryDto> postCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        CategoryDto createCategoryDto = adminService.postCategory(categoryDto);
        if(createCategoryDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(createCategoryDto);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtoList = adminService.getAllCategories();
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{title}")
    public ResponseEntity<List<CategoryDto>> getAllCategoriesByTitle(@PathVariable String title) {
        List<CategoryDto> categoryDtoList = adminService.getAllCategoriesByTitle(title);
        if(categoryDtoList == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDtoList);
    }

    @PostMapping("/{categoryId}/product")
    public ResponseEntity<ProductDto> postProduct(@PathVariable Long categoryId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto createProductDto = adminService.postProduct(categoryId, productDto);
        if(createProductDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(createProductDto);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductByCategory(@PathVariable Long categoryId) {
        List<ProductDto> productDtoListDto = adminService.getAllProductsByCategory(categoryId);
        if(productDtoListDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoListDto);
    }

    @GetMapping("/{categoryId}/product/{title}")
    public ResponseEntity<List<ProductDto>> getProductByCategoryAndTitle(@PathVariable Long categoryId, @PathVariable String title) {
        List<ProductDto> productDtoListDto = adminService.getProductByCategoryAndTitle(categoryId, title);
        if(productDtoListDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDtoListDto);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        adminService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ProductDto productDto = adminService.getProductById(productId);
        if(productDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable Long productId, ProductDto productDto) throws IOException {
        ProductDto updateProductDto = adminService.updateProductById(productId, productDto);
        if(updateProductDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(updateProductDto);
    }
}
