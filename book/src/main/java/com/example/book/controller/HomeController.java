package com.example.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/all")
    public String allBooks() {

        return "all"; // Widok all.html w katalogu templates
    }
}
