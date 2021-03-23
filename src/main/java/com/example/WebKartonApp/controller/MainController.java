package com.example.WebKartonApp.controller;


import com.example.WebKartonApp.model.News;
import com.example.WebKartonApp.repo.NewsRepository;
import com.example.WebKartonApp.service.NewsService;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
public class MainController {

    private final ProductService productService;
    private final NewsService newsService;
    private final NewsRepository newsRepository;


    @Autowired
    public MainController(ProductService productService, NewsService newsService, NewsRepository newsRepository) {
        this.productService = productService;
        this.newsService = newsService;
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllNews(@RequestParam(value = "page", required = false,defaultValue = "1") Integer page) {

        Page<News> news = newsRepository.findAll(PageRequest.of(page,
                10));

        return new ResponseEntity<>(news, HttpStatus.OK);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<?> getNews(@PathVariable("id") Long newsId) {
        News news = newsService.getOne(newsId);

        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
