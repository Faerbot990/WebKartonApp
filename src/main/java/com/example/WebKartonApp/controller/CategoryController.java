package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.dto.read.CategoryDto;
import com.example.WebKartonApp.dto.read.SubCategoryDto;
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
    public ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categories = categoryService.findAll(Sort.Direction.DESC,"localDate");
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId) {

        CategoryDto category = categoryService.getOne(categoryId);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @GetMapping("/subcategories")
    public ResponseEntity<List<SubCategoryDto>> getAllSubCategory() {
        List<SubCategoryDto> subCategories = subCategoryService.findAll(Sort.Direction.DESC,"localDate");
        return new ResponseEntity<>(subCategories, HttpStatus.OK);
    }
    @GetMapping("/subcategory/{id}")
    public ResponseEntity<SubCategoryDto> getSubCategory(@PathVariable("id") Long subCategoryId) {

        SubCategoryDto subCategory = subCategoryService.getOne(subCategoryId);
        return new ResponseEntity<>(subCategory, HttpStatus.OK);
    }

}
