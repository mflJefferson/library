package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Publisher;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;

@Service("authorService")
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public Author createAuthor(String name, String bookTitle, String bookDesc, String bookIsbn, String publisher) {
        Publisher pub = publisherRepository.findPublisherByName(publisher);
        if (pub == null) {
            pub = new Publisher();
            pub.setName(publisher);
            pub.setBooks(new HashSet<>());
        }
        Author author = new Author();
        author.setName(name);
        author.setBooks(new HashSet<>());

        Book book = new Book();
        Date created_at = new Date();

        book.setTitle(bookTitle);
        book.setDescription(bookDesc);
        book.setIsbn(bookIsbn);
        book.setCreated_at(created_at);

        book.setAuthor(author);
        book.setPublisher(pub);

        author.getBooks().add(book);
        pub.getBooks().add(book);

        authorRepository.save(author);
        publisherRepository.save(pub);
        bookRepository.save(book);

        return author;
    }
}
