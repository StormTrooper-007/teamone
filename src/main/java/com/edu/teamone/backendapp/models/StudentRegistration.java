package com.edu.teamone.backendapp.models;

import java.util.ArrayList;
import java.util.List;

import com.edu.teamone.backendapp.security.AppUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class StudentRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    
    @OneToOne
    @JoinColumn(name = "user_id",nullable = false)
    private AppUser student;

    private Long AppUserId;

    
    @ManyToMany
    @JoinTable(
        name = "student_course_registration",
        joinColumns = @JoinColumn(name = "student_id"),
        inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<CourseDetails> coursesRegistered = new ArrayList<>();

    @OneToMany(mappedBy = "studentAssignedTo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Assignment> assignments = new ArrayList<>();
}
