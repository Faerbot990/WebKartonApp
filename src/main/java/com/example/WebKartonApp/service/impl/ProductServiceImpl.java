package com.example.WebKartonApp.service.impl;

import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.model.SubCategory;
import com.example.WebKartonApp.repo.ProductRepository;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public void saveProductInfoById(String productName,
                                    SubCategory subCategory,
                                    String color,
                                    String description,
                                    Double price,
                                    String filename,
                                    String quantity,
                                    Long id) {
        productRepository.saveProductInfoById(productName, subCategory, color, quantity, price, description, filename, id);

    }

    @Override
    public Product save(Product product) {
        product.setLocalDate(LocalDate.now());
        return productRepository.save(product);
    }

//    @Override
//    public List<Product> findByCategorySlug(String slug) {
//        return productRepository.findByProductCategorySlug(slug);
//    }

    @Override
    public List<Product> findBySlug(String slug) {
        return productRepository.findBySlug(slug);
    }
}