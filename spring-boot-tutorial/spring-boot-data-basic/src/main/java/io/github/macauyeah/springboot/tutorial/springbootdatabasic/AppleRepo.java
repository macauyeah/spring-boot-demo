package io.github.macauyeah.springboot.tutorial.springbootdatabasic;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppleRepo extends JpaRepository<Apple, String>{
    
}