package com.example.book.controller;

import com.example.book.dto.BookRequest;
import com.example.book.entity.Book;
import com.example.book.service.ExternalBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final ExternalBookService externalBookService;

    @Autowired
    public ImportController(ExternalBookService externalBookService) {
        this.externalBookService = externalBookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> importBooks(@RequestParam String query) {
        List<Book> books = externalBookService.fetchAndSaveBooks(query);
        return ResponseEntity.ok(books);  // Zwracamy dane w formacie JSON???
    }


    // Nowa metoda do dodawania książki
    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody BookRequest bookRequest) {
        // Pobierz książkę z OpenLibrary
        Book book = externalBookService.fetchAndSaveBookDetails(bookRequest.getTitle());

        // Ustawienie oceny i recenzji
        book.setRating(bookRequest.getRating());
        book.setReview(bookRequest.getReview());

        // Zapisz książkę w bazie danych
        book = externalBookService.saveBook(book);

        return ResponseEntity.ok(book);
    }


}
