package com.example.WebKartonApp.dto.write;


import com.example.WebKartonApp.dto.read.CategoryBaseDto;
import com.example.WebKartonApp.dto.read.SubCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryChangeRequest {
    CategoryBaseDto dto;
}