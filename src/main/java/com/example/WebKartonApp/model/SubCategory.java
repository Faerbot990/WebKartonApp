package com.example.WebKartonApp.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "subcategory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Length(max = 255)
    private String subCategoryNameSlug;

    @Length(max = 255)
    private String subCategoryName;


    @Length(max = 999999)
    private String image;

//    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
//    @JsonBackReference
//    private Category parentCategory;

    private LocalDate localDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL//, mappedBy = "subcategoryId"
    )
    @JsonManagedReference
    private List<Product> products;

}
