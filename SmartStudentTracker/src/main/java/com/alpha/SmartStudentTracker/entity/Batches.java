package com.alpha.SmartStudentTracker.entity;

import org.hibernate.annotations.ManyToAny;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Batches {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String batchname;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Users trainer;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Courses course;

    public Batches() {
    }

    public Batches(Integer id, String batchname, Users trainer, Courses course) {
        this.id = id;
        this.batchname = batchname;
        this.trainer = trainer;
        this.course = course;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatchname() {
        return batchname;
    }

    public void setBatchname(String batchname) {
        this.batchname = batchname;
    }

    public Users getTrainer() {
        return trainer;
    }

    public void setTrainer(Users trainer) {
        this.trainer = trainer;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
    }
}
