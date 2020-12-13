package com.example.WebKartonApp.controller;



import com.example.WebKartonApp.model.Order;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.model.User;
import com.example.WebKartonApp.service.OrderService;
import com.example.WebKartonApp.service.ProductService;
import com.example.WebKartonApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final UserService userService;

    private final ProductService productService;

    private final OrderService orderService;

    @Autowired
    public AdminController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostMapping("/admin/add_prod")
    public ResponseEntity<?> addProduct(
            @Valid Product product,
            BindingResult bindingResult,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            saveFile(product, file);

            Product savedProduct = productService.save(product);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }
    }

    @PostMapping("/admin/update_prod")
    public ResponseEntity<?> updateProduct(
            @Valid Product product,
            BindingResult bindingResult,
            @RequestPart(name = "file", required = false) MultipartFile file
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
        } else {
            saveFile(product, file);

            productService.saveProductInfoById(product.getProductName(),
                    product.getProductCategory(),
                    product.getProductDescription(),
                    product.getFilename(),
                    product.getPrice(),
                    product.getQuantity(),
                    product.getId());

            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    @GetMapping("/admin/orders")
    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderService.findAll();

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/admin/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId) {
        User user = userService.getOne(userId);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin/user/all")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userService.findAll();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    private void saveFile(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        if (file == null) {
            product.setFilename("empty.jpg");
        } else {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            product.setFilename(resultFilename);
        }
    }
}
