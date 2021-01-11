package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.Product;

import java.util.List;

public interface ProductService {
    Product getOne(Long id);

    List<Product> findAll();

    List<Product> filter(List<String> productName, List<String> category, List<Integer> prices);

    List<Product> findByProductOrderByPriceDesc(String product);

    List<Product> findByProductOrderByCategoryDesc(String product);

    void saveProductInfoById(String productName,
                             String category,
                             String color,
                             String description,
                             String filename,
                             Double price,
                             String quantity,
                             Long id);

    Product save(Product product);
}
