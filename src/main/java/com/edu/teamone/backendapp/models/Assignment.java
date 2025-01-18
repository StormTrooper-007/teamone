package com.edu.teamone.backendapp.models;

import com.edu.teamone.backendapp.security.AppUser;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String submissionUrl;
    private String feedback;
    private String grade;
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "student_registration_id", nullable = false)
    StudentRegistration studentAssignedTo;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    CourseDetails courseName;

    @ManyToOne
    @JoinColumn(name = "lecturer_id") // Track which lecturer graded this assignment
    private AppUser lecturer;

}
