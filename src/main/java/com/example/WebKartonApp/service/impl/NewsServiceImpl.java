package com.example.WebKartonApp.service.impl;

import com.example.WebKartonApp.model.News;
import com.example.WebKartonApp.repo.NewsRepository;
import com.example.WebKartonApp.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News getOne(Long id) {
        return newsRepository.getOne(id);
    }

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public void saveNewsInfoById(String title, String information, String filename, Long id) {

        newsRepository.updateNewsInfoById(title, information, filename, id);
    }

    @Override
    public News save(News news) {
        news.setLocalDate(LocalDate.now());
        return newsRepository.save(news);
    }
}
