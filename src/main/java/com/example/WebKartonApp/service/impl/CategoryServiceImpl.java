package com.example.WebKartonApp.service.impl;

import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.repo.CategoryRepository;
import com.example.WebKartonApp.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }
}
