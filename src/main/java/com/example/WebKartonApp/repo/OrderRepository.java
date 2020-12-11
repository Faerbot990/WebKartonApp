package com.example.WebKartonApp.repo;


import com.example.WebKartonApp.model.Order;
import com.example.WebKartonApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order,Long> {
    List<Order> findOrderByUser(User user);
}
