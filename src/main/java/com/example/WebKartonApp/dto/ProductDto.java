package com.example.WebKartonApp.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class ProductDto {
    private String productName;
    private String productColor;
    private String productDescription;
    private String filename;
    private Double price;
    private String quantity;
    private String categorySlug;
    private LocalDate localDate;
}
