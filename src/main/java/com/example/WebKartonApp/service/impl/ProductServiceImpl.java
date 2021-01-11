package com.example.WebKartonApp.service.impl;


import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.repo.ProductRepository;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Product> findByProductOrderByCategoryDesc(String productCategory) {
        return productRepository.findByProductCategoryOrderByPriceDesc(productCategory);
    }

    @Override
    public void saveProductInfoById(String productName,
                                    String category,
                                    String color,
                                    String description,
                                    String filename,
                                    Double price,
                                    String quantity,
                                    Long id) {
        productRepository.saveProductInfoById(productName,category,color,quantity,price,description,filename,id);

    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
