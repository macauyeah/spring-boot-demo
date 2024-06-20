package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.Book;

public interface BookRepo extends JpaRepository<Book, String> {
    List<Book> findAllByUuidIn(List<String> uuids);
}
