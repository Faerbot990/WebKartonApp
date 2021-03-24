package com.example.WebKartonApp.service;

import com.example.WebKartonApp.model.News;

import java.util.List;


public interface NewsService {
    News getOne(Long id);

    List<News> findAll();

    void saveNewsInfoById(String title,
                          String information,
                          String filename,
                          Long id);

    News save(News news);


}
