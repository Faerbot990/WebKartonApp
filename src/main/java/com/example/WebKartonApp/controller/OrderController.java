//package com.example.WebKartonApp.controller;
//
//
//
//import com.example.WebKartonApp.model.Order;
//import com.example.WebKartonApp.model.Product;
//import com.example.WebKartonApp.model.User;
//import com.example.WebKartonApp.service.OrderService;
//import com.example.WebKartonApp.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/v1/order")
//public class OrderController {
//
//    private final UserService userService;
//
//    private final OrderService orderService;
//
//    @Autowired
//    public OrderController(UserService userService, OrderService orderService) {
//        this.userService = userService;
//        this.orderService = orderService;
//    }
//
//    @GetMapping("/order")
//    public ResponseEntity<?> getOrder(@AuthenticationPrincipal User userSession) {
//        User user = userService.findByUsername(userSession.getUsername());
//        List<Product> productList = user.getProductList();
//
//        return new ResponseEntity<>(productList, HttpStatus.OK);
//    }
//
//    @PostMapping("/order")
//    public ResponseEntity<?> addOrder(
//            @AuthenticationPrincipal User userSession,
//            @Valid @RequestBody Order validOrder,
//            BindingResult bindingResult
//    ) {
//        User user = userService.findByUsername(userSession.getUsername());
//        Order order = new Order(user);
//
//        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//
//            return new ResponseEntity<>(errorsMap, HttpStatus.BAD_REQUEST);
//        } else {
//            order.getProductList().addAll(user.getProductList());
//            order.setTotalPrice(validOrder.getTotalPrice());
//            order.setFirstName(validOrder.getFirstName());
//            order.setLastName(validOrder.getLastName());
//            order.setEmail(validOrder.getEmail());
//            order.setPhoneNumber(validOrder.getPhoneNumber());
//
//            user.getProductList().clear();
//
//            orderService.save(order);
//        }
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @GetMapping("/finalize")
//    public ResponseEntity<?> finalizeOrder() {
//        List<Order> orderList = orderService.findAll();
//        Order orderIndex = orderList.get(orderList.size() - 1);
//
//        return new ResponseEntity<>(orderIndex.getId(), HttpStatus.OK);
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<?> getUserOrdersList(@AuthenticationPrincipal User userSession) {
//        User user = userService.findByUsername(userSession.getUsername());
//        List<Order> orders = orderService.findOrderByUser(user);
//
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//}