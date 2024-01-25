package com.restaurant.restaurant_spring.dtos;

import com.restaurant.restaurant_spring.enums.UserRole;
import lombok.Data;

import java.security.PrivateKey;

@Data
public class AuthenticationReponse {

    private String jwt;
    private UserRole userRole;
    private Long userId;
}
