package com.example.WebKartonApp.controller;

import com.example.WebKartonApp.dto.ProductDto;
import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.News;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.repo.CategoryRepository;
import com.example.WebKartonApp.repo.NewsRepository;
import com.example.WebKartonApp.repo.ProductRepository;
import com.example.WebKartonApp.service.CategoryService;
import com.example.WebKartonApp.service.NewsService;
import com.example.WebKartonApp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@RestController
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    private final NewsService newsService;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final NewsRepository newsRepository;
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/add_category")
    public ResponseEntity<?> addCategory(@RequestBody Category category, BindingResult bindingResult
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            category.setSlug(transliterate(category.getName()));
            category.setNameSubSlug(transliterate(category.getNameSub()));
            Category saveCategory = categoryService.save(category);

            log.debug("ADMIN added product to DB: id={}, product={}, category={}", category.getSlug(), category.getName(),
                    category.getParentCategory());

            return new ResponseEntity<>(saveCategory, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/delete_category")
    public void deleteProduct(@RequestBody Category category) {
        categoryRepository.delete(category);
    }

    @PostMapping("/add_prod")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            Category category = categoryService.getOne(productDto.getCategorySlug());

            Product product = new Product(//
                    transliterate(productDto.getProductName()), //
                    productDto.getProductName(), //
                    productDto.getProductColor(), //
                    productDto.getProductDescription(), //
                    productDto.getFilename(), //
                    productDto.getPrice(), //
                    productDto.getQuantity(), //
                    category,//
                    productDto.getLocalDate()//
            );
            Product savedProduct = productService.save(product);

            log.info("ADMIN added product to DB: id={}, product={}, category={}", savedProduct.getSlug(),
                    savedProduct.getProductName(), category);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }
    }

    // @PutMapping("/update_prod")
    // public ResponseEntity<?> updateProduct(
    // @RequestBody Product product,
    // BindingResult bindingResult
    // ) throws IOException {
    // if (bindingResult.hasErrors()) {
    // Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
    //
    // return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
    // } else {
    //// saveFile(product, file);
    //
    // productService.saveProductInfoById(product.getProductName(),
    // product.getProductCategory(),
    // product.getProductColor(),
    // product.getProductDescription(),
    // product.getPrice(),
    // product.getFilename(),
    // product.getQuantity(),
    // product.getId());
    //
    // return new ResponseEntity<>(HttpStatus.OK);
    // }
    // }

    @DeleteMapping("/delete_prod")
    public void deleteProduct(@RequestBody Product product) {
        productRepository.delete(product);
    }

    @PostMapping("/add_news")
    public ResponseEntity<?> addNews(@RequestBody News news, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {

            News savedNews = newsService.save(news);

            return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
        }
    }

    @PutMapping("/update_news")
    public ResponseEntity<?> updateNews(@RequestBody News news, BindingResult bindingResult
    )  {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {

            newsService.saveNewsInfoById(news.getTitle(), news.getInformation(), news.getFilename(), news.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @DeleteMapping("/delete_news")
    public void deleteNews(@RequestBody News news) {
        newsRepository.delete(news);
    }

    public static String transliterate(String message) {
        char[] abcCyr = { ' ', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р',
                'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е',
                'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ',
                'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        String[] abcLat = { "-", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p",
                "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "i", "", "e", "ju", "ja", "a", "b", "v", "g",
                "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts",
                "ch", "sh", "sch", "", "i", "", "e", "ju", "ja", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
                "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++) {
                if (message.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x]);
                }
            }
        }
        return builder.toString();
    }
}
