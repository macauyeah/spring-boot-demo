package io.github.macauyeah.springboot.tutorial.springbootdatadeletion.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.springbootdatadeletion.entity.Book;

public interface BookRepo extends JpaRepository<Book, UUID> {
    
}
