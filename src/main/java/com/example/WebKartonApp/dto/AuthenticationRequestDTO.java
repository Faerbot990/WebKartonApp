package com.example.WebKartonApp.dto;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    private String username;

    private String password;
}
