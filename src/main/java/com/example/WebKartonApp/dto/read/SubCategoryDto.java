package com.example.WebKartonApp.dto.read;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDto {
    private Long id;
    private String subCategoryName;
    private String image;
    private List<ProductDto> products;
    private LocalDate localDate;
}