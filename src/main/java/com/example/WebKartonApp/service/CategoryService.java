package com.example.WebKartonApp.service;


import com.example.WebKartonApp.dto.read.CategoryDto;
import com.example.WebKartonApp.model.Category;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CategoryService {
    CategoryDto getOne(Long id);

    List<CategoryDto> findAll(Sort.Direction desc, String localDate);
// List <Category> findByCategoryName(String name);
// List<Category> getSubcategoriesByCategorySlug(String slug);

    Category save(CategoryDto category);

// void savCategoryInfoById(String name,SubCategory subCategory, Long id);

}
