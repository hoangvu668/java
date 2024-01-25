package com.restaurant.restaurant_spring.dtos;

import com.restaurant.restaurant_spring.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String name;
    private String password;
    private String email;
    private UserRole userRole;
}
