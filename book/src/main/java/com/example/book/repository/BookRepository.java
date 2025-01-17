package com.example.book.repository;

import com.example.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Możesz tu dodać dodatkowe metody wyszukiwania, np.:
     List<Book> findByGenre(String genre);
     List<Book> findAllBooksByOrderByTitleAsc();
}