package com.example.WebKartonApp.dto.read;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryBaseDto {
    private Long id;
    private String subCategoryName;
    private String image;
    private LocalDate localDate;


    public SubCategoryDto toDto(){
        return new SubCategoryDto(id, subCategoryName, image, new ArrayList<>(), localDate);
    }
}