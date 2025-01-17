package com.example.book.repository;

import com.example.book.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Możesz tu dodać metody wyszukiwania, np.:
     List<Review> findByBookId(Long bookId);
     List<Review> findByBookTitle(String title);

}