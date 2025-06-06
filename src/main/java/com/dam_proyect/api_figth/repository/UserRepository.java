package com.dam_proyect.api_figth.repository;

import com.dam_proyect.api_figth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}
