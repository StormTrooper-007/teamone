package com.edu.teamone.backendapp.models;

import java.util.ArrayList;
import java.util.List;

import com.edu.teamone.backendapp.security.AppUser;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "courses")
public class CourseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String description;
    private Long userId;

   
    @ManyToMany(mappedBy = "coursesRegistered")
    private List<StudentRegistration> registeredStudents = new ArrayList<>();

    @OneToMany(mappedBy = "courseName")
    private List<CourseDetails> courses;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseMaterial> courseMaterials;

}
