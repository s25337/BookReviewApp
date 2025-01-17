package com.example.book.controller;

import com.example.book.entity.Book;
import com.example.book.service.ExternalBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/import")
public class ImportController {

    private final ExternalBookService externalBookService;

    public ImportController(ExternalBookService externalBookService) {
        this.externalBookService = externalBookService;
    }

    @GetMapping("/books")
    public List<Book> importBooks(@RequestParam String query) {
        return externalBookService.fetchAndSaveBooks(query);
    }
}
