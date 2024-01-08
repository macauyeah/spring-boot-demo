package io.github.macauyeah.jpaspecification.applicationcontext.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.macauyeah.jpaspecification.applicationcontext.entity.Student;

public interface StudentRepo extends JpaSpecificationExecutor<Student>, JpaRepository<Student, String>{
    
}
