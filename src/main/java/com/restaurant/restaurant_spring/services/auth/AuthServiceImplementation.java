package com.restaurant.restaurant_spring.services.auth;

import com.restaurant.restaurant_spring.dtos.SignupRequest;
import com.restaurant.restaurant_spring.dtos.UserDto;
import com.restaurant.restaurant_spring.entities.User;
import com.restaurant.restaurant_spring.enums.UserRole;
import com.restaurant.restaurant_spring.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImplementation implements AuthService{

    private final UserRepository userRepository;

    public AuthServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void createAdminAccount() {
        User adminAccount = userRepository.getByUserRole(UserRole.ADMIN);
        if(adminAccount == null) {
            User user = new User();
            user.setName("hoangvu");
            user.setEmail("hoangvu@test.com");
            user.setPassword(new BCryptPasswordEncoder().encode("qweasd"));
            user.setUserRole(UserRole.ADMIN);
            userRepository.save(user);
        }
    }
    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setEmail(signupRequest.getEmail());
        user.setUserRole(UserRole.CUSTOMER);
        User createUser = userRepository.save(user);
        UserDto creatUserDto = new UserDto();
        creatUserDto.setName(createUser.getName());
        creatUserDto.setId(createUser.getId());
        creatUserDto.setUserRole(createUser.getUserRole());
        creatUserDto.setEmail(createUser.getEmail());
        return creatUserDto;
    }
}
