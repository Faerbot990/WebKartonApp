package com.example.WebKartonApp.service.impl;

import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.repo.CategoryRepository;
import com.example.WebKartonApp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getOne(String slug) {
        return categoryRepository.getBySlug(slug);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getSubcategoriesByCategorySlug(String slug) {
        return categoryRepository.findByParentCategorySlug(slug);
    }
}
