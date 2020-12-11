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
    @Query("update Product p set p.productName = ?1, p.productCategory = ?2, p.quantity = ?3, p.price = ?4, " +
            "p.productDescription = ?5, p.filename = ?6 where p.id = ?7")

    void saveProductInfoById(String productName,
                             String productCategory,
                             String description,
                             String filename,
                             Integer price,
                             String quantity,
                             Long id);
}

