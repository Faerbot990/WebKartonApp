package com.example.WebKartonApp.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String productName;
    private String productColor;
    private String productDescription;
    private String filename;
    private Double price;
    private String quantity;
    private String categorySlug;
}
