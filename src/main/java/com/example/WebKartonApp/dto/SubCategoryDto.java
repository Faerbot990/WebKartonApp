package com.example.WebKartonApp.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDto {
    private Long id;
    private String subCategoryName;
    private String image;
    private Long categoryId;
    private LocalDate localDate;
}