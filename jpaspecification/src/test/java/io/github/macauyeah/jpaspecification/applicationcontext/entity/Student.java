package io.github.macauyeah.jpaspecification.applicationcontext.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
    @Id
    private String uuid;
    private String name;
    private Date birthDate;

    @OneToMany(mappedBy = "student")
    private List<StudentApplication> studentApplications = new ArrayList<>();

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<StudentApplication> getStudentApplications() {
        return studentApplications;
    }

    public void setStudentApplications(List<StudentApplication> studentApplications) {
        this.studentApplications = studentApplications;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
