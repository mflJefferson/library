package com.example.library.service;

import com.example.library.entity.Author;

public interface AuthorService {
    Author createAuthor(String name, String bookTitle, String bookDesc, String bookIsbn, String publisher);
}
