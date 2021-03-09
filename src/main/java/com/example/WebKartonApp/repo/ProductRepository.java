package com.example.WebKartonApp.repo;



import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProductNameInAndProductCategoryInOrderByPriceDesc(List<String> productName, List<String> category);

    List<Product> findByProductNameInOrProductCategoryInOrderByPriceDesc(List<String> productName, List<String> category);

    List<Product> findByPriceBetweenOrderByPriceDesc(Integer startingPrice, Integer endingPrice);

    List<Product> findByProductNameOrderByPriceDesc(String productName);

    List<Product> findByProductCategoryOrderByPriceDesc(String productCategory);

    @Modifying
    @Transactional
    @Query("update Product p set p.productName = ?1, p.productCategory = ?2, p.productColor =?3, p.quantity = ?4, p.price = ?5, " +
            "p.productDescription = ?6, p.filename =?7  where p.id = ?8")
    void saveProductInfoById(String productName,
                             Category productCategory,
                             String productColor,
                             String quantity,
                             Double price,
                             String productDescription,
                             String filename, Long id);
}