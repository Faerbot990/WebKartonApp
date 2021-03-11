package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.Product;

import java.util.List;

public interface CategoryService {
 Category getOne(Long id);

 List<Category> findAll();


 Category save(Category category);
}
