package com.example.library.controller;

import com.example.library.entity.Book;
import com.example.library.service.BookServiceImpl;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

@SpringBootApplication
@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @GetMapping("")
    @JsonView(View.Book.class)
    public ResponseEntity<Collection<Book>> getBooks() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> getOneBook(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getOneBook(id), HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> postBook(@RequestBody Book book, UriComponentsBuilder uriComponentsBuilder) {
        Book newBook = bookService.createBook(book);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/book/" + newBook.getId()).build().toUri());

        return new ResponseEntity<>(newBook, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> putBook(@RequestBody Book book, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder) {
        Book existentBook = bookService.getOneBook(id);
        Book updatedBook = bookService.updateBook(existentBook, book);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/book/" + updatedBook.getId()).build().toUri());

        return new ResponseEntity<>(updatedBook, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @JsonView(View.Book.class)
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        Book deletedBook = bookService.deleteBook(id);

        return new ResponseEntity<>(deletedBook, HttpStatus.OK);
    }

}
