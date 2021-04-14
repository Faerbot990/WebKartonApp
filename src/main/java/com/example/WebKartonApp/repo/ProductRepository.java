package com.example.WebKartonApp.repo;



import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.Product;
import com.example.WebKartonApp.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySlug(String slug);


//    @Modifying
//    @Transactional
//    @Query("update Product p set p.productName = ?1, p.subCategory = ?2, p.productColor =?3, p.quantity = ?4, p.price = ?5, " +
//            "p.productDescription = ?6, p.filename =?7  where p.id = ?8")
//    void saveProductInfoById(String productName,
//                             SubCategory subCategory,
//                             String productColor,
//                             String quantity,
//                             Double price,
//                             String productDescription,
//                             String filename,
//                             Long id);
}