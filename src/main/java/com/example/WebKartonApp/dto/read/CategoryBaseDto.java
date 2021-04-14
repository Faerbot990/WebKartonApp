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
public class CategoryBaseDto {
    private Long id;
    private String slug;
    private String name;
    private String image;
    private LocalDate localDate;

    public CategoryDto toDto(){
        return new CategoryDto(id, slug, name, image, localDate, new ArrayList<>());
    }
}