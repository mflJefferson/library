package com.example.library.repository;

import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    public List<Book> findBookByAuthorAndTitleContains(Author author, String title);
}
