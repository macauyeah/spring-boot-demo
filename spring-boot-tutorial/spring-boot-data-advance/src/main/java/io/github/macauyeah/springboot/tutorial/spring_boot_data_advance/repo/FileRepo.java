package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.FileEntity;

public interface FileRepo extends JpaRepository<FileEntity, UUID> {

}
