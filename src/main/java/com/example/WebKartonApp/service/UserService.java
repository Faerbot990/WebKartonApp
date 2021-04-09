package com.example.WebKartonApp.service;


import com.example.WebKartonApp.model.User;

import java.util.List;


public interface UserService {
    User getOne(Long id);

    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);
}
