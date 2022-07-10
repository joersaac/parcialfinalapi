package com.grupo16.src.repositories;

import com.grupo16.src.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Category findOneByName(String name);
}
