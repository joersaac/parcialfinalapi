package com.grupo16.src.services;

import com.grupo16.src.models.entities.Category;

public interface CategoryService {
    Category findOneByName(String name) throws Exception;
}
