package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.SubCategory;
import com.example.WebKartonApp.service.CategoryService;
import com.example.WebKartonApp.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CategoryController {
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories = categoryService.findAll(Sort.Direction.DESC,"localDate");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategory(@PathVariable("id") Long categoryId) {

        Category category = categoryService.getOne(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @GetMapping("/subcategories")
    public ResponseEntity<?> getAllSubCategory() {
        List<SubCategory> subCategories = subCategoryService.findAll(Sort.Direction.DESC,"localDate");
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
    @GetMapping("/subcategory/{id}")
    public ResponseEntity<?> getSubCategory(@PathVariable("id") Long subCategoryId) {

        SubCategory subCategory = subCategoryService.getOne(subCategoryId);
        return new ResponseEntity<>(subCategory, HttpStatus.OK);
    }

}
