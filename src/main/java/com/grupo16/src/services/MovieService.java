package com.grupo16.src.services;

import com.grupo16.src.models.dtos.PageableDTO;
import com.grupo16.src.models.entities.Category;
import com.grupo16.src.models.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Page<Movie> findAll(PageableDTO info);
    Page<Movie> findByCategory(Category category, PageableDTO info);
    Movie findOneByTitle(String title);
    Movie findOneById(String code);
}
