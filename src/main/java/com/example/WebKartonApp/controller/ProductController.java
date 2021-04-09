package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.News;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.model.SubCategory;
import com.example.WebKartonApp.service.CategoryService;
import com.example.WebKartonApp.service.ProductService;
import com.example.WebKartonApp.service.SubCategoryService;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.List;

@Data
@Controller
@RequestMapping("/")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping
    public String getIndex(Model model) {
        return "index";
    }




    @GetMapping("/categories/{categorySlug}")
    public String getCategory(@PathVariable String categorySlug, Model model) {
//    Category category = categoryService.getOne(categorySlug);
//    List<Category> subCategories = categoryService.getSubcategoriesByCategorySlug(categorySlug);
//    List<Product> products = productService.findByCategorySlug(categorySlug);

//    model.addAttribute("category", category);
//    model.addAttribute("subcategories", subCategories);
//    model.addAttribute("products", products);

        return "catalog";
    }


    @GetMapping("/categories/{categorySlug}/{subCategorySlug}")
    public String getSubCategory(@PathVariable String categorySlug, @PathVariable String subCategorySlug, Model model) {
//    Category category = categoryService.getOne(subCategorySlug);
        List<Category> subCategories = Collections.emptyList();
//    List<Product> products = productService.findByCategorySlug(subCategorySlug);

//    model.addAttribute("category", category);

        model.addAttribute("subcategories", subCategories);
//    model.addAttribute("products", products);

        return "catalog";
    }

//    @GetMapping("/products/{productSlug}")
//    public String getSubCategory(@PathVariable String productSlug, Model model) {
//        model.addAttribute("product", productService.findBySlug(productSlug).get(0));
//
//        return "product";
//    }
}