package com.example.WebKartonApp.controller;


import com.example.WebKartonApp.model.News;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.service.NewsService;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/main")
public class MainController {

    private final ProductService productService;
    private final NewsService newsService;

    @Autowired
    public MainController(ProductService productService, NewsService newsService) {
        this.productService = productService;
        this.newsService = newsService;
    }

    @GetMapping
    public ResponseEntity<?> getAllNews() {
        List<News> news = newsService.findAll();

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<?> getNews(@PathVariable("id") Long newsId) {
        News news = newsService.getOne(newsId);

        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
