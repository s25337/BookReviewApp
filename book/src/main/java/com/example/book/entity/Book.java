package com.example.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;

    @Column(name = "published_date")
    private LocalDate publishedDate;

    @ManyToOne(cascade = CascadeType.PERSIST) // Kaskadowe zapisywanie autora
    @JoinColumn(name = "author_id")
    @JsonIgnoreProperties("books")  // Ignorujemy listę książek w autorze
    private Author author;


    private String review;
    
    @Column(name = "rating")
    private int rating;

    public Book(String title, String genre, Author author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }
    public void setAuthor(Author author) {
      this.author = author;
    }
    // Getters and Setters

    public Book() {
    }
    public LocalDate getPublishedDate() {
        return publishedDate;
    }
    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setRating(int rating) {
        this.rating=rating;
    }

    public int getRating() {
        return rating;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setAuthor(String s) {
        this.author = new Author(s);
    }
}
