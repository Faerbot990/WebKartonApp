package com.example.WebKartonApp.service;


import com.example.WebKartonApp.dto.read.SubCategoryDto;
import com.example.WebKartonApp.model.SubCategory;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SubCategoryService {
    SubCategoryDto getOne(Long id);

    List<SubCategoryDto> findAll(Sort.Direction desc, String localDate);
//
// List<Category> getSubcategoriesByCategorySlug(String slug);

    SubCategory save(SubCategoryDto subCategory);
}
