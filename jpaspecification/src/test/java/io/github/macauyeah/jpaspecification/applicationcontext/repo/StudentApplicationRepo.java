package io.github.macauyeah.jpaspecification.applicationcontext.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import io.github.macauyeah.jpaspecification.applicationcontext.entity.StudentApplication;

public interface StudentApplicationRepo extends JpaRepository<StudentApplication, String>, JpaSpecificationExecutor<StudentApplication> {
    
}
