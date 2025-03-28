package io.github.macauyeah.springboot.tutorial.springbootdatabasic;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppleRepo extends JpaRepository<Apple, String> {
    Optional<Apple> findFirstByWeight(Double weight);
}