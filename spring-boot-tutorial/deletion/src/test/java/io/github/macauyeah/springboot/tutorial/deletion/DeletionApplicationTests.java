package io.github.macauyeah.springboot.tutorial.deletion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.macauyeah.springboot.tutorial.deletion.entity.Author;
import io.github.macauyeah.springboot.tutorial.deletion.entity.Book;
import io.github.macauyeah.springboot.tutorial.deletion.repo.AuthorRepo;
import io.github.macauyeah.springboot.tutorial.deletion.repo.BookRepo;

@SpringBootTest
class DeletionApplicationTests {
	@Autowired
	AuthorRepo authorRepo;

	@Autowired
	BookRepo bookRepo;

	private Author targetAuthor;
	private Book targetBook;

	@Test
	void deleteCascadeWithoutRefreshWillFail() {
		assertEquals(1, authorRepo.count());
		assertEquals(1, bookRepo.count());
		assertThrows(org.springframework.dao.InvalidDataAccessApiUsageException.class,()->{
			authorRepo.delete(targetAuthor);
		});
		// need to manually delete child then parent;
		bookRepo.delete(targetBook);
		authorRepo.delete(targetAuthor);
	}

	@Test
	void deleteCascade() {
		assertEquals(1, authorRepo.count());
		assertEquals(1, bookRepo.count());
		Author author = authorRepo.findAll().get(0);
		// apply (CascadeType.REMOVE) or (orphanRemoval = true) work for this case
		authorRepo.delete(author);
		assertEquals(0, authorRepo.count());
	}

	@BeforeEach
	private void prepare(){
		Author author = new Author();
		author.setName("test name");
		authorRepo.save(author);

		targetAuthor = author;

		Book book = new Book();
		book.setAuthor(author);
		bookRepo.save(book);
		authorRepo.save(author);

		targetBook = book;
	}

	@AfterEach
	private void checkCount(){
		assertEquals(0, bookRepo.count());
	}

}
