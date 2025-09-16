package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.Book;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.BookSearch;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.BookSearchJoin;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo.BookRepo;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo.BookSearchJoinRepo;
import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo.BookSearchRepo;

@SpringBootTest
class SpringBootDataAdvanceApplicationTests {
	@Autowired
	BookRepo bookRepo;
	@Autowired
	BookSearchRepo bookSearchRepo;
	@Autowired
	BookSearchJoinRepo bookSearchJoinRepo;

	@BeforeEach
	void setUp(){
		bookRepo.deleteAll();
		bookSearchRepo.deleteAll();
	}

	@Test
	void BookCRUDTest() {
		Book book = new Book();
		bookRepo.save(book);
		assertNotNull(book.getUuid());
		bookRepo.findById(book.getUuid()).orElseThrow();
		bookRepo.delete(book);
	}

	@Test
	void BookSearchCRUDTest() {
		BookSearch bookSearch = new BookSearch();
		bookSearchRepo.save(bookSearch);
		assertNotNull(bookSearch.getUuid());
		bookSearchRepo.findById(bookSearch.getUuid()).orElseThrow();
		bookSearchRepo.delete(bookSearch);
	}

	@Test
	void TestLongListFail() {
		List<Book> books = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			Book book = new Book();
			books.add(book);
		}
		bookRepo.saveAll(books);
		List<String> uuidList = books.stream().map(Book::getUuid).toList();
		assertThrows(DataIntegrityViolationException.class,
				() -> {
					bookRepo.findAllByUuidIn(uuidList);
				});
		;
	}

	@Test
	void TestLongListByJoin() {
		List<Book> books = new ArrayList<>();
		for (int i = 0; i < 1000000; i++) {
			Book book = new Book();
			books.add(book);
		}
		bookRepo.saveAll(books);

		// want to search book by long list of uuid, but it is too large
		// so put them into other table, apply batch num and join method
		// too query the original book by join result
		// BookSearchJoin should include all column of Book.
		String batchNum = UUID.randomUUID().toString();
		List<BookSearch> bookSearchList = books.stream().map(book->{
			BookSearch bookSearch = new BookSearch();
			bookSearch.setBatchSearchNum(batchNum);
			bookSearch.setSearchBookUuid(book.getUuid());
			return bookSearch;
		}).toList();
		bookSearchRepo.saveAll(bookSearchList);
		List<BookSearchJoin> bookSearchJoins = bookSearchJoinRepo.findAllByBatchSearchNum(batchNum);
		assertEquals(1000000, bookSearchJoins.size());
	}

}
