package com.example.library.entity;

import com.example.library.controller.View;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.Author.class)
    private long id;

    @Column(name = "name")
    @JsonView({View.Author.class, View.Book.class})
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    @JsonView(View.Author.class)
    private Set<Book> books;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
