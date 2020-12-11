package com.example.WebKartonApp.service;




import com.example.WebKartonApp.model.Order;
import com.example.WebKartonApp.model.User;

import java.util.List;

public interface OrderService {
    List<Order> findAll();

    Order save(Order order);

    List<Order> findOrderByUser(User user);
}
