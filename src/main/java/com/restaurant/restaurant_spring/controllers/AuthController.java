package com.restaurant.restaurant_spring.controllers;

import com.restaurant.restaurant_spring.dtos.AuthenticationReponse;
import com.restaurant.restaurant_spring.dtos.AuthenticationRequest;
import com.restaurant.restaurant_spring.dtos.SignupRequest;
import com.restaurant.restaurant_spring.dtos.UserDto;
import com.restaurant.restaurant_spring.entities.User;
import com.restaurant.restaurant_spring.repositories.UserRepository;
import com.restaurant.restaurant_spring.services.auth.AuthService;
import com.restaurant.restaurant_spring.services.auth.jwt.UserDetailsServiceImpl;
import com.restaurant.restaurant_spring.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, UserRepository userRepository) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        UserDto createUserDto = authService.createUser(signupRequest);
        if(createUserDto == null) {
            return new ResponseEntity<>("User not created, Come again later", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationReponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        } catch (DisabledException d) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        Optional<User> userOptional = userRepository.findByEmail(userDetails.getUsername());
        AuthenticationReponse authenticationReponse = new AuthenticationReponse();
        if(userOptional.isPresent()) {
            authenticationReponse.setJwt(jwt);
            authenticationReponse.setUserRole(userOptional.get().getUserRole());
            authenticationReponse.setUserId(userOptional.get().getId());
        }
        return authenticationReponse;
    }
}
