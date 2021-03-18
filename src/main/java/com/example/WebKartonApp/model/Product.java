package com.example.WebKartonApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.datetime.DateFormatter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "slug", "productName", "productColor", "productDescription", "price" })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product {
    @Id
    private String slug;

    @NotBlank(message = "")
    @Length(max = 255)
    private String productName;

    @NotBlank(message = "")
    private String productColor;

    @NotBlank(message = "")
    @Length(max = 2048)
    private String productDescription;

    @Length(max = 999999)
    private String filename;

    @NotNull(message = "")
    private Double price;

    @NotBlank(message = "")
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_slug")
    private Category productCategory;

    private LocalDate localDate;

}
