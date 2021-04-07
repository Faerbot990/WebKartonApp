package com.example.WebKartonApp.repo;

import com.example.WebKartonApp.model.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
//    Page<News> findAll(Pageable pageable);
    @Modifying
    @Transactional
    @Query("update News n set n.title = ?1, n.information = ?2, n.filename = ?3 where n.id = ?4")
    void updateNewsInfoById(String title,
                            String information,
                            String filename,
                            Long id);

}

