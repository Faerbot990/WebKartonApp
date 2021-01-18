package com.example.WebKartonApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.File;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "productName", "productCategory","productColor", "productDescription", "price"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "")
    @Length(max = 255)
    private String productName;

    @NotBlank(message = "")
    private String productCategory;

    @NotBlank(message = "")
    private String subCategory;

    @NotBlank(message = "")
    private String productColor;

    @NotBlank(message = "")
    @Length(max = 255)
    private String productDescription;

    @NotBlank(message = "")
    @Length(max = 255)
    private String filename;

    @NotNull(message = "")
    private Double price;

    @NotBlank(message = "")
    private String quantity;
}
