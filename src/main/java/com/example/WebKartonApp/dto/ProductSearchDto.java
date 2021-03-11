package com.example.WebKartonApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductSearchDto {
    List<Integer> prices;
    List<String> productName;
    List<String> productCategory;
}
