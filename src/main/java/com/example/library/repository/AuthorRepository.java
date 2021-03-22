package com.example.library.repository;

import com.example.library.entity.Author;
import com.example.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select au from Author au join Book b on b.author = au join Publisher p on b.publisher = ?1 group by au.id")
    public List<Author> searchByPublisher(Publisher publisher);

}
