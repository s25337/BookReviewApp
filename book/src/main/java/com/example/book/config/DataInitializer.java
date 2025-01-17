package com.example.book.config;


import com.example.book.entity.Author;
import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(BookRepository bookRepository) {
        return args -> {
            // Przykładowe książki
            bookRepository.save(new Book("The Catcher in the Rye", "Fiction", new Author("J.D. Salinger")));
            bookRepository.save(new Book("To Kill a Mockingbird", "Fiction", new Author("Harper Lee")));
            bookRepository.save(new Book("1984", "Dystopian", new Author("George Orwell")));
            bookRepository.save(new Book("The Great Gatsby", "Fiction", new Author("F. Scott Fitzgerald")));
            bookRepository.save(new Book("Moby-Dick", "Adventure", new Author("Herman Melville")));
        };
    }
}