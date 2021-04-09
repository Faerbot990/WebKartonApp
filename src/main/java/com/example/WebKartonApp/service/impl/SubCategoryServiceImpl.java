package com.example.WebKartonApp.service.impl;

import com.example.WebKartonApp.model.SubCategory;
import com.example.WebKartonApp.repo.SubCategoryRepository;
import com.example.WebKartonApp.service.SubCategoryService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private final SubCategoryRepository subCategoryRepository;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public SubCategory getOne(Long id) {
        return subCategoryRepository.getOne(id);
    }

    @Override
    public List<SubCategory> findAll(Sort.Direction desc, String localDate) {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory save(SubCategory subCategory) {
        subCategory.setLocalDate(LocalDate.now());
        return subCategoryRepository.save(subCategory);
    }


}
