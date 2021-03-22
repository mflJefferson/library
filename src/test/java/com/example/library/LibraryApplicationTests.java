package com.example.library;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.Publisher;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.repository.PublisherRepository;
import com.example.library.service.AuthorService;
import com.example.library.service.AuthorServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@Transactional
@Rollback
class LibraryApplicationTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorServiceImpl authorService;

    @Test
    void contextLoads() {
    }

    @Test
    void testInsert() {
        Book book = new Book();
        Date created_at = new Date();
        book.setTitle("Animal Farm");
        book.setDescription("Some animals are more equals than others");
        book.setIsbn("978-6555521856");
        book.setCreated_at(created_at);

        bookRepository.save(book);

        Author author = new Author();
        author.setName("George Orwell");
        author.setBooks(new HashSet<>());
        author.getBooks().add(book);

        authorRepository.save(author);

        Publisher publisher = new Publisher();
        publisher.setName("Signet Book");
        publisher.setBooks(new HashSet<>());
        publisher.getBooks().add(book);

        publisherRepository.save(publisher);

        book.setAuthor(author);
        book.setPublisher(publisher);

        bookRepository.save(book);

        assertEquals(book.getId(), author.getBooks().iterator().next().getId());
        assertEquals(book.getId(), publisher.getBooks().iterator().next().getId());
        assertEquals(author.getId(), publisher.getBooks().iterator().next().getAuthor().getId());

    }

    @Test
    void testFindBookByAuthorAndTitleContains() {
        Author author = authorRepository.getOne(6L);
        List<Book> books = bookRepository.findBookByAuthorAndTitleContains(author, "Gelo e Fogo");
        assertEquals(5, books.size());
        assertEquals(6, author.getBooks().size());
    }

    @Test
    void testSearchByPublisher() {
        Publisher publisher = publisherRepository.getOne(3L);
        List<Author> authors = authorRepository.searchByPublisher(publisher);
        assertEquals(3, authors.size());
    }

    @Test
    void testAuthorService() {
        String name = "Robert C. Martin";
        String bookTitle = "Código Limpo";
        String bookIsbn = "978-8576082675";
        String bookDesc = "Mesmo um código ruim pode funcionar";
        String publisher = "Alta Books";

        Author author = authorService.createAuthor(name,
                bookTitle, bookDesc, bookIsbn, publisher);

        assertEquals(bookTitle, author.getBooks().iterator().next().getTitle());
        assertEquals(publisher, author.getBooks().iterator().next().getPublisher().getName());
    }

}
