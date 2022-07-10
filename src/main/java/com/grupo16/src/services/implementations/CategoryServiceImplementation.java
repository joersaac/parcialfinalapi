package com.grupo16.src.services.implementations;

import com.grupo16.src.models.entities.Category;
import com.grupo16.src.repositories.CategoryRepository;
import com.grupo16.src.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category findOneByName(String name) throws Exception {
        return categoryRepository.findOneByName(name);
    }
}
