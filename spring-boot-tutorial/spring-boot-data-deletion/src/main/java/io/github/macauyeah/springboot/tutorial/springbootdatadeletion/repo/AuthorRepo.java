package io.github.macauyeah.springboot.tutorial.springbootdatadeletion.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.springbootdatadeletion.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, UUID> {
    
}
