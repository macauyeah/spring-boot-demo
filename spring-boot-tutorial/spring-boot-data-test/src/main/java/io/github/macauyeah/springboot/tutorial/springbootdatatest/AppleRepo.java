package io.github.macauyeah.springboot.tutorial.springbootdatatest;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppleRepo extends JpaRepository<Apple, String>{
    
}