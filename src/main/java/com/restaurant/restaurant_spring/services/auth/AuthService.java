package com.restaurant.restaurant_spring.services.auth;

import com.restaurant.restaurant_spring.dtos.SignupRequest;
import com.restaurant.restaurant_spring.dtos.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
