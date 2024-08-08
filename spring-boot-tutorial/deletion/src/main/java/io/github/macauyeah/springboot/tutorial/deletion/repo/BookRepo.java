package io.github.macauyeah.springboot.tutorial.deletion.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.deletion.entity.Book;

public interface BookRepo extends JpaRepository<Book, UUID> {
    
}
