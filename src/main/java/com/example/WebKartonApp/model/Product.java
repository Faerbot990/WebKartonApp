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
    @Length(max = 255)
    private String productCategory;

    private String productDescription;
    /**файл изображеня*/
    private String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @NotNull(message = "")
    private Integer price;

    @NotBlank(message = "")
    @Length(max = 255)
    private String quantity;
}
