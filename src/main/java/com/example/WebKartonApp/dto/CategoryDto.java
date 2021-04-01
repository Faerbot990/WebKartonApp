package com.example.WebKartonApp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CategoryDto {
    private Long id;
    private String name;
    private long subcategoryId;
    private LocalDate localDate;
}
