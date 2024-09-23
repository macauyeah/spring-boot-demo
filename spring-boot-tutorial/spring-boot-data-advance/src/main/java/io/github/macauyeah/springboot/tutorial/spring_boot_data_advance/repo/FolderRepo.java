package io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.spring_boot_data_advance.entity.FolderEntity;

public interface FolderRepo extends JpaRepository<FolderEntity, UUID>{
}
