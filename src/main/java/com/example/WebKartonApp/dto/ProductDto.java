package com.example.WebKartonApp.dto;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ProductDto {
    private Long id;
    private String productName;
    private String productColor;
    private String productDescription;
    private String filename;
    private Double price;
    private String quantity;
    private LocalDate localDate;
    private long subcategoryId;
//    private long categoryId;
}
