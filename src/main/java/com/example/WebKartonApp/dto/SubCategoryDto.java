package com.example.WebKartonApp.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
public class SubCategoryDto {
    private Long id;
    private String subCategoryName;
    private String image;
    private long categoryId;
    private LocalDate localDate;
}
