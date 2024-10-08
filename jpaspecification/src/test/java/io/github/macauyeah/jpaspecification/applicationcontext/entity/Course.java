package io.github.macauyeah.jpaspecification.applicationcontext.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Course {
    @Id
    private String uuid;
    private Date createdDate;
    private Integer credit;
    private Double learningHours;

    public Double getLearningHours() {
        return learningHours;
    }

    public void setLearningHours(Double learningHours) {
        this.learningHours = learningHours;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @OneToMany(mappedBy = "course")
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

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

}
