package com.example.WebKartonApp.service.impl;

import com.example.WebKartonApp.dto.read.CategoryDto;
import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.repo.CategoryRepository;
import com.example.WebKartonApp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;


    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto getOne(Long id) {
        return mapper.map(categoryRepository.getOne(id), CategoryDto.class) ;
    }

    @Override
    public List<CategoryDto> findAll(Sort.Direction desc, String localDate) {
        return categoryRepository.findAll()
                .stream()
                .map( p -> mapper.map(p, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Category save(CategoryDto dto) {
        dto.setLocalDate(LocalDate.now());
        return categoryRepository.save(mapper.map(dto, Category.class));
    }

}
