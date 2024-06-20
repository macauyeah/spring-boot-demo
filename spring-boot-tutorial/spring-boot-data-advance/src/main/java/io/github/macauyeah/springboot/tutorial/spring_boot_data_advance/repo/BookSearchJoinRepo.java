package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.BookSearchJoin;

public interface BookSearchJoinRepo extends JpaRepository<BookSearchJoin, String> {
    List<BookSearchJoin> findAllByBatchSearchNum(String batchSearchNum);
}
