package com.grupo16.src.repositories;

import com.grupo16.src.models.entities.Category;
import com.grupo16.src.models.entities.Movie;
import com.grupo16.src.models.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, String> {
   Page<Movie> findByCategory(Category category, Pageable pageable);
}
