package com.example.book.service;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExternalBookService {

    private final RestTemplate restTemplate;
    private final BookRepository bookRepository;

    public ExternalBookService(RestTemplate restTemplate, BookRepository bookRepository) {
        this.restTemplate = restTemplate;
        this.bookRepository = bookRepository;
    }

    public List<Book> fetchAndSaveBooks(String query) {
        String url = "https://openlibrary.org/search.json?q=" + query;
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        List<Map<String, Object>> docs = (List<Map<String, Object>>) response.get("docs");
        List<Book> books = docs.stream().map(doc -> {
            Book book = new Book();
            book.setTitle((String) doc.get("title"));
            book.setGenre("Unknown");
            return book;
        }).collect(Collectors.toList());

        return bookRepository.saveAll(books);
    }
}
