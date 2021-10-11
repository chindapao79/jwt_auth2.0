package com.example.demo.model.entities;

import lombok.Data;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "student_register")
@Data
public class StudentRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private String age;
    private String address;
    private String pob;
    private String dob;
    private String description;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createaAt;

    @PrePersist
    public void onCreate() {
        createaAt = new Date();
    }

    @Column(name = "updated_at")
    private Date updatedAt;

}
