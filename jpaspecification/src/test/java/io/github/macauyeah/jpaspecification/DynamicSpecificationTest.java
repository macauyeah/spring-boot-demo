package io.github.macauyeah.jpaspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.macauyeah.jpaspecification.applicationcontext.Application;
import io.github.macauyeah.jpaspecification.applicationcontext.entity.Course;
import io.github.macauyeah.jpaspecification.applicationcontext.entity.Student;
import io.github.macauyeah.jpaspecification.applicationcontext.entity.StudentApplication;
import io.github.macauyeah.jpaspecification.applicationcontext.repo.CourseRepo;
import io.github.macauyeah.jpaspecification.applicationcontext.repo.StudentApplicationRepo;
import io.github.macauyeah.jpaspecification.applicationcontext.repo.StudentRepo;

@SpringBootTest(classes = Application.class)
public class DynamicSpecificationTest { // class name must ending with *Test / *Tests / *TestCase
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private StudentApplicationRepo studentAppRepo;
    @Autowired
    private CourseRepo courseRepo;

    @Test
    void testSearchAnd() {
        SearchSchema studentSchema = new SearchSchema();
        studentSchema.setStringValues(Map.of("uuid", "10000"));
        studentSchema.setDateLessThan(Map.of("birthDate", new Date()));
        studentSchema.setSubStringValues(Map.of("name", "NOTEXISIT"));
        assert 0L == studentRepo.count(
                DynamicSpecification.searchWithAnd(Student.class, studentSchema));

        studentSchema.setStringValues(Map.of("uuid", "10000"));
        studentSchema.setDateLessThan(Map.of("birthDate", new Date()));
        studentSchema.setSubStringValues(Map.of("name", "Dia"));
        assert 1L == studentRepo.count(
                DynamicSpecification.searchWithAnd(Student.class, studentSchema));

        SearchSchema courseSchema = new SearchSchema();
        courseSchema.setStringValues(Map.of("uuid", "course-100"));
        courseSchema.setDateGreaterThan(Map.of("createdDate", new Date()));

        SearchSchema studentAppSchema = new SearchSchema();
        studentSchema.setJoinValues(Map.of("studentApplications", studentAppSchema));
        studentAppSchema.setJoinValues(Map.of("course", courseSchema));

        assert 0L == studentRepo.count(
                DynamicSpecification.searchWithAnd(Student.class, studentSchema));

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2023);
        cal.set(Calendar.DAY_OF_YEAR, 180);
        courseSchema.setStringValues(Map.of("uuid", "course-101"));
        courseSchema.setDateGreaterThan(Map.of("createdDate", cal.getTime()));
        // createdDate >= middle of 2023
        assert 1L == studentRepo.count(
                DynamicSpecification.searchWithAnd(Student.class, studentSchema));

        BetweenSchema<Date> dateBetween = new BetweenSchema<>();
        dateBetween.setLowerBound(cal.getTime());
        dateBetween.setUpperBound(new Date());
        courseSchema.setDateBetween(Map.of("createdDate", dateBetween));
        // createdDate between middle of 2023 and now
        // also include previous createdDate >= middle of 2023
        assert 1L == studentRepo.count(
                DynamicSpecification.searchWithAnd(Student.class, studentSchema));

        cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2024);
        cal.set(Calendar.DAY_OF_YEAR, 2);
        dateBetween.setLowerBound(cal.getTime());
        dateBetween.setUpperBound(new Date());
        courseSchema.setDateBetween(Map.of("createdDate", dateBetween));
        // createdDate between 2024-01-02 and now
        // also include previous createdDate >= middle of 2023

        assert 0L == studentRepo.count(
                DynamicSpecification.searchWithAnd(Student.class, studentSchema));
    }

    @Test
    void testSearchOr() {
        // search student only, two field with one field correct;
        SearchSchema studentSchema = new SearchSchema();
        studentSchema.setStringValues(Map.of("uuid", "10000"));
        studentSchema.setSubStringValues(Map.of("name", "NOTEXISIT"));
        assertEquals(1L, studentRepo.count(
                DynamicSpecification.searchWithOr(Student.class, studentSchema)));

        // search with join, join field all correct;
        studentSchema.setStringValues(Map.of());
        studentSchema.setSubStringValues(Map.of());
        SearchSchema courseSchema = new SearchSchema();
        courseSchema.setStringValues(Map.of("uuid", "course-101"));
        SearchSchema studentAppSchema = new SearchSchema();
        studentSchema.setJoinValues(Map.of("studentApplications", studentAppSchema));
        studentAppSchema.setJoinValues(Map.of("course", courseSchema));

        assertEquals(1L, studentRepo.count(
                DynamicSpecification.searchWithOr(Student.class, studentSchema)));

        // search with join, all wrong
        studentSchema.setStringValues(Map.of("uuid", "20000"));
        studentSchema.setSubStringValues(Map.of("name", "NOTEXISIT"));
        courseSchema.setStringValues(Map.of("uuid", "course-200"));
        assertEquals(0L, studentRepo.count(
                DynamicSpecification.searchWithOr(Student.class, studentSchema)));
    }

    @BeforeEach
    void initStaffRepo() {
        Student student = new Student();
        student.setUuid("10000");
        student.setName("Chan Dia Man");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        student.setBirthDate(cal.getTime());
        studentRepo.save(student);

        Course course = new Course();
        course.setUuid("course-101");
        cal.set(Calendar.YEAR, 2024);
        course.setCreatedDate(cal.getTime());
        courseRepo.save(course);

        StudentApplication studentApplication = new StudentApplication();
        studentApplication.setUuid("course-101-10000");
        studentApplication.setCourse(course);
        studentApplication.setStudent(student);
        studentAppRepo.save(studentApplication);
    }
}
