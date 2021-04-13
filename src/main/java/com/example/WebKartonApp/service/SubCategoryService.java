package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.SubCategory;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SubCategoryService {
    SubCategory getOne(Long id);

    List<SubCategory> findAll(Sort.Direction desc, String localDate);
//
// List<Category> getSubcategoriesByCategorySlug(String slug);

    SubCategory save(SubCategory subCategory);
}
