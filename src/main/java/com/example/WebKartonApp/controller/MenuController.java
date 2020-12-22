package com.example.WebKartonApp.controller;



import com.example.WebKartonApp.dto.ProductSearchDto;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    private final ProductService productService;

    @Autowired
    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/search")
    public ResponseEntity<?> findProductsByFilterParams(@RequestBody ProductSearchDto filterDto) {
        List<Product> filter = productService.filter(filterDto.getProductName(), filterDto.getProductCategory(), filterDto.getPrices());

        return new ResponseEntity<>(filter, HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<?> findByProductCategory(@RequestBody ProductSearchDto filterDto) {
        List<Product> category = productService.findByProductOrderByCategoryDesc(String.valueOf(filterDto.getProductCategory()));

        return new ResponseEntity<>(category, HttpStatus.OK);
    }

}
