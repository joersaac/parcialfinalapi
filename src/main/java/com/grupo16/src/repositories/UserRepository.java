package com.grupo16.src.repositories;

import com.grupo16.src.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsernameOrEmail(String username, String email);
    User findOneByUsername(String username);
}
