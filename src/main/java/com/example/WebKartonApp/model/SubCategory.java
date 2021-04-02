package com.example.WebKartonApp.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "subcategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "id","subCategoryName", "subCategoryNameSlug", "image"})
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 255)
    private String subCategoryName;

    @Length(max = 255)
    private String subCategoryNameSlug;

    @Length(max = 999999)
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category productCategory;

    private LocalDate localDate;

}
