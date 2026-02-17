package com.jpa.concepts.jpa_concepts.service;

import com.jpa.concepts.jpa_concepts.DTO.CategoryDto;
import com.jpa.concepts.jpa_concepts.Entity.Category;

import java.util.List;

public interface CategoryService {



    CategoryDto createCategory(CategoryDto category);

    CategoryDto updateCategory(Long categoryId, CategoryDto category);

    List<CategoryDto> getAll();

    CategoryDto get(Long categoryId);

    void deletecategory(Long categoryId);
}
