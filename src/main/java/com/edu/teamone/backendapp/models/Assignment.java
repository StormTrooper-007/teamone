package com.edu.teamone.backendapp.models;

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
    
    @ManyToOne
    @JoinColumn(name = "student_registration_id", nullable = false)
    StudentRegistration studentAssignedTo;
    
}
