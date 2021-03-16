package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.Product;

import java.util.List;

public interface CategoryService {
 Category getOne(String slug);

 List<Category> findAll();

 List<Category> getSubcategoriesByCategorySlug(String slug);


 Category save(Category category);
}
