package com.example.WebKartonApp.repo;


import com.example.WebKartonApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameInAndProductCategoryInOrderByPriceDesc(List<String> productName, List<String> category);

    List<Product> findByProductNameInOrProductCategoryInOrderByPriceDesc(List<String> productName, List<String> category);

    List<Product> findByPriceBetweenOrderByPriceDesc(Integer startingPrice, Integer endingPrice);

    List<Product> findByProductNameOrderByPriceDesc(String productName);

    List<Product> findByProductCategoryOrderByPriceDesc(String productCategory);

    @Modifying
    @Transactional
    @Query("update Product p set p.productName = ?1, p.productCategory = ?2, p.subCategory = ?3,p.productColor =?4, p.quantity = ?5, p.price = ?6, " +
            "p.productDescription = ?7, p.filename = ?8 where p.id = ?9")
    void saveProductInfoById(String productName,
                             String productCategory,
                             String subCategory,
                             String productColor,
                             String quantity,
                             Double price,
                             String productDescription,
                             String filename,
                             Long id);
}

