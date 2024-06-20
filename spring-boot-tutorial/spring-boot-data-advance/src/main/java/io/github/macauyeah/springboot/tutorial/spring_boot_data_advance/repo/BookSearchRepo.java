package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.BookSearch;

public interface BookSearchRepo extends JpaRepository<BookSearch, String> {
    
}
