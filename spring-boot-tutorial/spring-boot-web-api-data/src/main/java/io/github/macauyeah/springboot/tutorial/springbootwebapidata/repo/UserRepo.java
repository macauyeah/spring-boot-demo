package io.github.macauyeah.springboot.tutorial.springbootwebapidata.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.springboot.tutorial.springbootwebapidata.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findOneByUsername(String username);
}
