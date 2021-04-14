package com.example.WebKartonApp.service;


import com.example.WebKartonApp.dto.read.ProductDto;
import com.example.WebKartonApp.model.Product;

import java.util.List;

public interface ProductService {
    ProductDto getOne(Long id);

    List<ProductDto> findAll();

    List<ProductDto> findBySlug(String slug);

    Product save(ProductDto product);
}