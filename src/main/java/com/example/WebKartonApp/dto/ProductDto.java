package com.example.WebKartonApp.dto;

import com.example.WebKartonApp.model.Category;
import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String productName;
    private String productColor;
    private String productDescription;
    private String filename;
    private Double price;
    private String quantity;

    private long categoryId;

}
