//package com.example.WebKartonApp.model;
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.*;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "orders")
//@Getter
//@Setter
//@NoArgsConstructor
//@EqualsAndHashCode(of = {"id","user","productList"})
//public class Order {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Double totalPrice;
//
//    private LocalDate date;
//
//    @NotBlank(message = "")
//    private String firstName;
//
//    @NotBlank(message = "")
//    private String lastName;
//
//    @Email(message = "Incorrect Email")
//    @NotBlank(message = "")
//    private String email;
//
//    @NotBlank(message = "")
//    private String phoneNumber;
//
//    @OrderColumn
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Product> productList;
//
//    @ManyToOne
//    private User user;
//
//    public Order(User user) {
//        this.date = LocalDate.now();
//        this.productList = new ArrayList<>();
//        this.user = user;
//    }
//}
