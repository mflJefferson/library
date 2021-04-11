package com.example.library.service;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        Book new_book = new Book();

        new_book.setTitle(book.getTitle());
        new_book.setIsbn(book.getIsbn());
        new_book.setDescription(book.getDescription());

        bookRepository.save(new_book);

        return new_book;
    }

    @Override
    public Collection<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getOneBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new ResourceNotFoundException("Livro não existe");
    }

    @Override
    public Book updateBook(Book book, Book newBook) {
        book.setDescription(newBook.getDescription());
        book.setTitle(newBook.getTitle());
        book.setIsbn(newBook.getIsbn());

        book.setUpdated_at(new Date());

        bookRepository.save(book);

        return book;
    }

    @Override
    public Book addAuthor(Book book, Author author) {
        book.setAuthor(author);
        author.getBooks().add(book);

        bookRepository.save(book);
        return book;
    }

    public Book deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(book.get().getId());
            return book.get();
        }
        throw new ResourceNotFoundException("Livro não existe");
    }
}
