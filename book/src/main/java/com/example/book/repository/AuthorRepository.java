package com.example.book.repository;

import com.example.book.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // Możesz tu dodać metody wyszukiwania, np.:
     List<Author> findByNameContaining(String name);
     List<Author> findByBooksAuthorId(Long id);

}