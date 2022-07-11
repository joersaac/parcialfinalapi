package com.grupo16.src.services.implementations;

import com.grupo16.src.models.dtos.PageableDTO;
import com.grupo16.src.models.entities.Category;
import com.grupo16.src.models.entities.Movie;
import com.grupo16.src.repositories.MovieRepository;
import com.grupo16.src.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImplementation implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Page<Movie> findAll(PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(), Sort.by("title").ascending());
        return movieRepository.findAll(request);
    }

    @Override
    public Page<Movie> findByCategory(Category category, PageableDTO info) {
        PageRequest request = PageRequest
                .of(info.getPage(), info.getLimit(), Sort.by("title").ascending());
        return movieRepository.findByCategory(category,request);
    }

    @Override
    public Movie findOneByTitle(String title) {
        return movieRepository.findOneByTitle(title);
    }

    @Override
    public Movie findOneById(String code) {
        return movieRepository.findOneByCode(code);
    }
}
