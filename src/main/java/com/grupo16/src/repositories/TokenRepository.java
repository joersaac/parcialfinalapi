package com.grupo16.src.repositories;

import com.grupo16.src.models.entities.Token;
import com.grupo16.src.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findByUserAndActive(User user, Boolean active);

}
