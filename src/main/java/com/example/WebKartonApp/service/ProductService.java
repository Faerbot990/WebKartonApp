package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.Product;

import java.util.List;

public interface ProductService {
    Product getOne(Long id);

    List<Product> findAll();

    List<Product> filter(List<String> productName, List<String> category, List<Integer> prices);

    List<Product> findByProductOrderByPriceDesc(String product);

    List<Product> findByProductOrderByNameDesc(String product);

    List<Product> findByProductOrderByCategoryDesc(String product);

    List<Product> findByCategorySlug(String slug);

    List<Product> findBySlug(String slug);

//    List<Product> getProductsByCategoryName(String name);
//
//    List<String> getSubcategoriesByCategoryName(String name);
//
//    List<Product> getProductsBySubcategoryName(String name);
//
//    Product getProductBySlug(String productSlug);


    void saveProductInfoById(String productName,
                             Category productCategory,
                             String color,
                             String description,
                             Double price,
                             String filename,
                             String quantity,
                             Long id);

    Product save(Product product);
}
