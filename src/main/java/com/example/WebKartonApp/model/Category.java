package com.example.WebKartonApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = { "slug", "name", "slug" })
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Category {
    @Id
    private String slug;

    @Length(max = 255)
    private String name;

    @Length(max = 200000)
    private String image;

    @Length(max = 255)
    private String nameSub;

    @Length(max = 255)
    private String nameSubSlug;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_slug", referencedColumnName = "slug")
    private Category parentCategory;

}
