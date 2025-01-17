package com.example.book.controller;

import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final BookRepository bookRepository;

    public HomeController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/all")
    public String allBooks( Model books) {
    //dodaje ksiazki
        books.addAttribute("books", bookRepository.findAll());
        return "all"; // Widok all.html w katalogu templates
    }
}
