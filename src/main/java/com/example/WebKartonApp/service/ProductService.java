package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.Product;

import java.util.List;

public interface ProductService {
    Product getOne(Long id);

    List<Product> findAll();

    List<Product> filter(List<String> productName, List<String> category, List<Integer> prices);

    List<Product> findByProductOrderByPriceDesc(String product);

    List<Product> findByProductOrderByNameDesc(String product);

    List<Product> findByProductOrderByCategoryDesc(String product);



    void saveProductInfoById(String productName,
                             String category,
                             String subCategory,
                             String color,
                             String description,
                             Double price,
                             String filename,
                             String quantity,
                             Long id);

    Product save(Product product);
}
