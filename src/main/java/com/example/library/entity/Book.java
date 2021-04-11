package com.example.library.entity;

import com.example.library.controller.View;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonView(View.Book.class)
    private long id;

    // Autor
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    @JsonView({View.Book.class})
    private Author author;

    // Editora
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @Column(name = "title")
    @JsonView({View.Book.class, View.Author.class})
    private String title;

    @Column(name = "isbn")
    @JsonView(View.Book.class)
    private String isbn;

    @Column(name = "description")
    @Type(type = "text")
    @JsonView(View.Book.class)
    private String description;

    @Column(name = "created_at")
    @JsonView({View.Book.class, View.Author.class})
    private Date created_at;

    @Column(name = "updated_at")
    @JsonView(View.Book.class)
    private Date updated_at;

    public Book() {
        this.setCreated_at(new Date());
        this.setUpdated_at(new Date());
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
