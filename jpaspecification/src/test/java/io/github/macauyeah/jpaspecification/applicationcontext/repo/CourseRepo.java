package io.github.macauyeah.jpaspecification.applicationcontext.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.macauyeah.jpaspecification.applicationcontext.entity.Course;

public interface CourseRepo extends JpaRepository<Course, String> {
    
}
