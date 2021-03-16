package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.service.CategoryService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Data
@Controller
@RequestMapping("/menu")
public class ViewController {
    private final CategoryService categoryService;

    @GetMapping
    public String getIndex() {
        return "index";
    }
//
//    @GetMapping
//    public String getAllCategory(Model model, @PathVariable String categoryList ) {
//        model.addAttribute("categoryList", categoryService.findAll());
//        List<Category> categoryList = categoryService.findAll();
//        return "catalog";
//    }


}
