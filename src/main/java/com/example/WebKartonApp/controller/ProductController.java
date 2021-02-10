package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.service.ProductService;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Data
@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "catalog";
    }

    @GetMapping("/{category}")
    public String getCategory(@PathVariable String category, Model model) {
        model.addAttribute("product", productService.getCategoryByName(category));
        model.addAttribute("subcategory", productService.getSubcategoriesByName(category));
        return "product";
    }
}
//    @GetMapping("/{id}")
//    public String getById(@PathVariable long id, Model model) {
//        model.addAttribute("product", productService.getOne(id));
//        return "product";
//    }
