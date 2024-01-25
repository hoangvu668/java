package com.restaurant.restaurant_spring.repositories;

import com.restaurant.restaurant_spring.entities.User;
import com.restaurant.restaurant_spring.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    User getByUserRole(UserRole userRole);
}
