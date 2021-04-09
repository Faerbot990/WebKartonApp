package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.service.CategoryService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Controller
@RequestMapping("/menu")
public class ViewController {
    private final CategoryService categoryService;

    @GetMapping
    public String getIndex() {
        return "index";
    }
}
