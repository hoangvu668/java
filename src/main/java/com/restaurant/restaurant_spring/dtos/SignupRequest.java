package com.restaurant.restaurant_spring.dtos;

import lombok.Data;

@Data
public class SignupRequest {
    private String name;
    private String email;
    private String password;
}
