package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.SubCategory;

import java.util.List;

public interface SubCategoryService {
 SubCategory getOne(Long id);



 List<SubCategory> findAll();
//
// List<Category> getSubcategoriesByCategorySlug(String slug);


 SubCategory save(SubCategory subCategory);
}
