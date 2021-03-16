package com.example.WebKartonApp.repo;

import com.example.WebKartonApp.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category getBySlug(String slug);

  List<Category> findBySlug(String slug);

  List<Category> findByParentCategorySlug(String slug);
}
