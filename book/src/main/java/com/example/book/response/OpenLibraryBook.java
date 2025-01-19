package com.example.book.response;

import java.util.List;
import java.util.Map;

public class OpenLibraryBook {
    private String title;
    private List<String> author_name;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(List<String> author_name) {
        this.author_name = author_name;
    }
}
