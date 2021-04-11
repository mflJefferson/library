package com.example.library.service;

import com.example.library.entity.Author;

import java.util.Collection;

public interface AuthorService {
    Author createAuthor(String name, String bookTitle, String bookDesc, String bookIsbn, String publisher);

    Collection<Author> getAuthors();

    Author getOneAuthor(Long id);

    Author updateAuthor(Author author, String name);
}
