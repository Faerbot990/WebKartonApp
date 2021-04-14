package com.example.WebKartonApp.dto.write;

import com.example.WebKartonApp.dto.read.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductChangeRequest {
    private ProductDto productDto;
    private Long subcategoryId;
}
