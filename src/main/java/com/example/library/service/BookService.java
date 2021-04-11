package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;

import java.util.Collection;

public interface BookService {
    Book createBook(Book book);

    Collection<Book> getBooks();

    Book getOneBook(Long id);

    Book updateBook(Book book, Book newBook);

    Book addAuthor(Book book, Author author);

    Book deleteBook(Long id);
}
