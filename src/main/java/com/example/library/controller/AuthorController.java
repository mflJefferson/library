package com.example.library.controller;

import com.example.library.entity.Author;
import com.example.library.service.AuthorServiceImpl;
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
@RequestMapping("/author")
@CrossOrigin
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping("")
    @JsonView(View.Author.class)
    public ResponseEntity<Collection<Author>> getAuthors() {
        return new ResponseEntity<>(authorService.getAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> getOneAuthor(@PathVariable Long id) {
        return new ResponseEntity<>(authorService.getOneAuthor(id), HttpStatus.OK);
    }

    @PostMapping("")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> postAuthor(@RequestBody Author author, UriComponentsBuilder uriComponentsBuilder) {

        Author newAuthor = authorService.createSingleAuthor(author.getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/author/" + newAuthor.getId()).build().toUri());

        return new ResponseEntity<>(newAuthor, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @JsonView(View.Author.class)
    public ResponseEntity<Author> putAuthor(@RequestBody Author author, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder) {
        Author existentAuthor = authorService.getOneAuthor(id);
        Author updatedAuthor = authorService.updateAuthor(existentAuthor, author.getName());

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path(
                        "/author/" + updatedAuthor.getId()).build().toUri());

        return new ResponseEntity<>(updatedAuthor, headers, HttpStatus.CREATED);
    }
}
