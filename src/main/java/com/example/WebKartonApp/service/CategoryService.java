package com.example.WebKartonApp.service;



import com.example.WebKartonApp.model.Category;

import java.util.List;

public interface CategoryService {
 Category getOne(Long id);


 List<Category> findAll();
// List <Category> findByCategoryName(String name);
// List<Category> getSubcategoriesByCategorySlug(String slug);


 Category save(Category category);


// void savCategoryInfoById(String name,SubCategory subCategory, Long id);
}
