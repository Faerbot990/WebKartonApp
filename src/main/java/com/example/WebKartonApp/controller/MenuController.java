package com.example.WebKartonApp.controller;


import com.example.WebKartonApp.dto.read.ProductDto;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class MenuController {
    private final ProductService productService;

    @Autowired
    public MenuController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        List<ProductDto> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
