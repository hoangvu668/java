package com.restaurant.restaurant_spring.dtos;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private MultipartFile img;
    private byte[] returnedImg;
}
