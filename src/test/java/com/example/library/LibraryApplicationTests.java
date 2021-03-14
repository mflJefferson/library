package com.example.library;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Date;

@SpringBootTest
@Transactional
@Rollback
class LibraryApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void testInsert() {
		Book book = new Book();
		Date created_at = new Date();
		book.setTitle("A revolução dos bichos");
		book.setDescription("Some animals are more equals than others");
		book.setIsbn("978-6555521856");
		book.setCreated_at(created_at);

		bookRepository.save(book);

		assertNotNull(book.getId());
	}

}
