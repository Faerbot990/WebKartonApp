package com.example.WebKartonApp.service;

import com.example.WebKartonApp.model.News;
import com.example.WebKartonApp.model.Product;

import java.util.List;


public interface NewsService {
    News getOne(Long id);

    List<News> findAll();

    void saveNewsInfoById(String Title,
                          String Information,
                          String filename,
                          Long id);

    News save(News news);


}
