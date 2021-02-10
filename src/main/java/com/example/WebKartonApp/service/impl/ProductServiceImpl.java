package com.example.WebKartonApp.service.impl;


import com.example.WebKartonApp.model.Categories;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.repo.ProductRepository;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getOne(Long id) {
        return productRepository.getOne(id);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> filter(List<String> productName, List<String> category, List<Integer> prices) {
        List<Product> productList;

        if (!prices.isEmpty()) {
            productList = productRepository.findByPriceBetweenOrderByPriceDesc(prices.get(0), prices.get(1));
        } else if (!productName.isEmpty() && !category.isEmpty()) {
            productList = productRepository.findByProductNameInAndProductCategoryInOrderByPriceDesc(productName, category);
        } else if (!productName.isEmpty() || !category.isEmpty()) {
            productList = productRepository.findByProductNameInOrProductCategoryInOrderByPriceDesc(productName, category);
        } else {
            productList = productRepository.findAll();
        }
        return productList;
    }



    @Override
    public List<Product> findByProductOrderByPriceDesc(String productName) {
        return productRepository.findByProductNameOrderByPriceDesc(productName);
    }

    @Override
    public List<Product> findByProductOrderByNameDesc(String product) {
        return null;
    }

    @Override
    public List<Product> findByProductOrderByCategoryDesc(String productCategory) {
        return productRepository.findByProductCategoryOrderByPriceDesc(productCategory);
    }

    @Override
    public Product getCategoryByName(String name) {
        List<Product> cat = productRepository
                .findAll()
                .stream()
                .filter(category -> category.getProductCategory().equals(name))
                .collect(Collectors.toList());



        return productRepository
                .findAll()
                .stream()
                .filter(category -> category.getProductCategory().equals(name))
                .collect(Collectors.toList())
                .get(0);
    }


    @Override
    public List<Product> getSubcategoriesByName(String name) {
        return productRepository
                .findAll()
                .stream()
                .filter(category -> category.getSubCategory() != null && category.getSubCategory().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void saveProductInfoById(String productName,
                                    String category,
                                    String subCategory,
                                    String color,
                                    String description,
                                    Double price,
                                    String filename,
                                    String quantity,
                                    Long id) {
        productRepository.saveProductInfoById(productName,category,subCategory, color,quantity,price,description,filename,id);

    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
