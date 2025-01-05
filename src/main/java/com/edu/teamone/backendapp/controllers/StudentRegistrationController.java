package com.edu.teamone.backendapp.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.teamone.backendapp.dtos.StudentRegistrationDTO;
import com.edu.teamone.backendapp.services.StudentRegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class StudentRegistrationController {

    private final StudentRegistrationService studentRegistrationService;


    @PostMapping("/{username}/registercourse/{courseId}")
    public ResponseEntity<StudentRegistrationDTO> registerCourse(
        @PathVariable String username,
         @PathVariable Long courseId
        ){
        try {
            StudentRegistrationDTO student = studentRegistrationService.registerForCourse(username, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentRegistrationDTO>> getAllRegisteredStudents() {
        try {
            List<StudentRegistrationDTO> students = studentRegistrationService.getRegisteredStudents();
            return ResponseEntity.status(HttpStatus.OK).body(students);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("{registrationId}/addcourse/{courseId}")
    public ResponseEntity<StudentRegistrationDTO> addCourseToRegistration(
            @PathVariable Long registrationId,
            @PathVariable Long courseId) {
        try {
            StudentRegistrationDTO registration = studentRegistrationService.addCourseToRegistration(registrationId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(registration);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


    @DeleteMapping("{registrationId}/removecourse/{courseId}")
    public ResponseEntity<Void> removeCourseRegistration(
            @PathVariable Long registrationId,
            @PathVariable Long courseId) {
        try {
            studentRegistrationService.removeCourseRegistration(registrationId, courseId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
