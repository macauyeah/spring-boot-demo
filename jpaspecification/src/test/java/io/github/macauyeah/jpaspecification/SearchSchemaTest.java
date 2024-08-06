package io.github.macauyeah.jpaspecification;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.macauyeah.jpaspecification.applicationcontext.Application;
import io.github.macauyeah.jpaspecification.applicationcontext.entity.Student;
import io.github.macauyeah.jpaspecification.applicationcontext.repo.StudentRepo;
import io.github.macauyeah.jpaspecification.pojo.StudentFilter;

@SpringBootTest(classes = Application.class)
public class SearchSchemaTest {
    @Autowired
    private StudentRepo studentRepo;

    @Test
    void testAddStringValuesByReflection() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        SearchSchema searchSchema = new SearchSchema();
        StudentFilter studentFilter = new StudentFilter();
        studentFilter.setName("Chan Dia Man");
        searchSchema.addStringValuesByReflection(List.of("name"), studentFilter);
        long studentCount = studentRepo.count(DynamicSpecification.search(Student.class, searchSchema));
        assertEquals(1L, studentCount);

        studentFilter = new StudentFilter();
        studentFilter.setName("Chan Dia Man");
        studentFilter.setUuid("10000");
        searchSchema.addStringValuesByReflection(List.of("name", "uuid"), studentFilter);
        studentCount = studentRepo.count(DynamicSpecification.search(Student.class, searchSchema));
        assertEquals(1L, studentCount);

        studentFilter = new StudentFilter();
        studentFilter.setName("Chan Dia");
        studentFilter.setUuid("10000");
        searchSchema.addStringValuesByReflection(List.of("name", "uuid"), studentFilter);
        studentCount = studentRepo.count(DynamicSpecification.search(Student.class, searchSchema));
        assertEquals(0L, studentCount);
    }

    @BeforeEach
    void initStudentRepo() {
        Student student = new Student();
        student.setUuid("10000");
        student.setName("Chan Dia Man");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2000);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        student.setBirthDate(cal.getTime());
        studentRepo.save(student);
    }
}
