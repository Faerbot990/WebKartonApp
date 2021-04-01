package com.example.WebKartonApp.repo;

import com.example.WebKartonApp.model.Category;
import com.example.WebKartonApp.model.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
