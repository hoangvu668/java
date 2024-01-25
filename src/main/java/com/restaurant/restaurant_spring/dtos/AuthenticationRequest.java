package com.restaurant.restaurant_spring.dtos;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
}
