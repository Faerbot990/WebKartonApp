package com.example.WebKartonApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id", "productName", "productColor", "productDescription", "price"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product   {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "")
    @Length(max = 255)
    private String productName;

    @NotBlank(message = "")
    private String productColor;

    @NotBlank(message = "")
    @Length(max = 2048)
    private String productDescription;

    @Length(max = 200000)
    private String filename;

    @NotNull(message = "")
    private Double price;

    @NotBlank(message = "")
    private String quantity;

//    @NotBlank(message = "")
//    @Length(max = 255)
//    private String productCategory;
    @ManyToOne
    private Category productCategory;
//    @OneToMany (mappedBy = "product")
//    private Set<Category> category;

}
