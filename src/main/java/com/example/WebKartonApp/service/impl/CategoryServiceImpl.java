package com.example.WebKartonApp.service.impl;



import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.repo.CategoryRepository;
import com.example.WebKartonApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category getOne(Long id) {
        return categoryRepository.getOne(id);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        category.setLocalDate(LocalDate.now());
        return categoryRepository.save(category);
    }

//    @Override
//    public void savCategoryInfoById(String name, SubCategory subCategory, Long id) {
//        categoryRepository.saveCategoryInfoById(name, subCategory, id);
//    }

    //    @Override
//    public List<Category> getSubcategoriesByCategorySlug(String slug) {
//        return categoryRepository.findByParentCategorySlug(slug);
//    }
}
