package io.github.macauyeah.springboot.tutorial.deletion.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.deletion.entity.Author;

public interface AuthorRepo extends JpaRepository<Author, UUID> {
    
}
