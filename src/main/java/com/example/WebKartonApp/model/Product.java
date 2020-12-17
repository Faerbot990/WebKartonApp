package com.example.WebKartonApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ={"id", "productName","productCategory", "productDescription", "price" })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "")
    @Length(max = 255)
    private String productName;

    @NotBlank(message = "")
    private String productCategory;

    @NotBlank(message = "")
    @Length(max = 255)
    private String productDescription;

    @NotBlank(message = "")
    @Length(max = 255)
    private String filename;

    @NotNull(message = "")
    private Integer price;

    @NotBlank(message = "")
    @Length(max = 255)
    private String quantity;
}
