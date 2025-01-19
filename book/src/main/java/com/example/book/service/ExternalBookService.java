package com.example.book.service;

import com.example.book.entity.Author;
import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import com.example.book.response.OpenLibraryBook;
import com.example.book.response.OpenLibraryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExternalBookService {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<Book> fetchAndSaveBooks(String query) {
        String url = "https://openlibrary.org/search.json?q=" + query;

        // zapytania do API
        OpenLibraryResponse response = restTemplate.getForObject(url, OpenLibraryResponse.class);

        if (response == null) {
            throw new RuntimeException("Failed to fetch books from OpenLibrary");
        }

        // Zapisanie
        List<Book> books = response.getDocs().stream()
                .map(doc -> {
                    Book book = new Book();
                    book.setTitle(doc.getTitle());
                    book.setAuthor(doc.getAuthor_name() != null ? doc.getAuthor_name().get(0) : "Unknown");
                    book.setGenre("Unknown");
                    book.setPublishedDate(null); // Można dodać logikę do ustawienia daty publikacji
                    return bookRepository.save(book);
                })
                .collect(Collectors.toList());

        return books;
    }



    public Book fetchAndSaveBookDetails(String title) {
        // Wywołanie API OpenLibrary, aby pobrać dane książki
        String url = "https://openlibrary.org/search.json?q=" + title;

        // Wykonanie zapytania i mapowanie odpowiedzi na OpenLibraryResponse
        OpenLibraryResponse response = restTemplate.getForObject(url, OpenLibraryResponse.class);

        // Jeśli nie znaleziono książki
        if (response == null || response.getDocs().isEmpty()) {
            throw new RuntimeException("Book not found in OpenLibrary");
        }

        // Załóżmy, że dostajemy książkę
        OpenLibraryBook doc = response.getDocs().get(0); // Pierwsza książka w odpowiedzi
        Book book = new Book();
        book.setTitle(doc.getTitle());

        // Sprawdzenie, czy lista autorów nie jest pusta
        if (doc.getAuthor_name() != null && !doc.getAuthor_name().isEmpty()) {
            book.setAuthor(new Author(doc.getAuthor_name().get(0))); // Ustawiamy autora, jeśli jest dostępny
        } else {
            book.setAuthor(new Author("Unknown")); // Ustawiamy domyślnego autora
        }

        book.setGenre("Unknown"); // Możesz dodać logikę do ustawienia gatunku
        book.setPublishedDate(null); // Możesz dodać logikę do ustawienia daty publikacji
        return book;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
