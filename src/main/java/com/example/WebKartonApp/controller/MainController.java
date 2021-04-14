package com.example.WebKartonApp.controller;


import com.example.WebKartonApp.model.News;
import com.example.WebKartonApp.repo.NewsRepository;
import com.example.WebKartonApp.service.NewsService;
import com.example.WebKartonApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/main")
public class MainController {

    private final NewsService newsService;
    private final NewsRepository newsRepository;

    @Autowired
    public MainController(NewsService newsService, NewsRepository newsRepository) {
        this.newsService = newsService;
        this.newsRepository = newsRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllNews(
//            @RequestParam(value = "page", required = false,defaultValue = "1") Integer page
    ) {
        List<News> newsPage = newsRepository.findAll();

        return new ResponseEntity<>(newsPage, HttpStatus.OK);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<?> getNews(@PathVariable("id") Long newsId) {
        News news = newsService.getOne(newsId);

        return new ResponseEntity<>(news, HttpStatus.OK);
    }
}
