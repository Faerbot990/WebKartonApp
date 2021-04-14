package com.example.WebKartonApp.dto.write;

import com.example.WebKartonApp.dto.read.SubCategoryBaseDto;
import com.example.WebKartonApp.dto.read.SubCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryChangeRequest {
    private SubCategoryBaseDto subCategoryDto;
    private Long categoryId;

}
