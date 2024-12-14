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

import com.edu.teamone.backendapp.models.StudentRegistration;
import com.edu.teamone.backendapp.services.StudentRegistrationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class StudentRegistrationController {

    private final StudentRegistrationService studentRegistrationService;


    @PostMapping("/{username}/registercourse/{courseId}")
    public ResponseEntity<StudentRegistration> registerCourse(
        @PathVariable String username,
         @PathVariable Long courseId
        ){
        try{
            StudentRegistration student = studentRegistrationService
            .registerForCourse(username, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(student);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<List<StudentRegistration>> getAllRegisteredStudents(){
        try{
            List<StudentRegistration> res = studentRegistrationService.getRegisteredStudent();
            return ResponseEntity.status(HttpStatus.OK).body(res);

        }catch(NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("{registrationId}/addcourse/{courseId}")
    public ResponseEntity<StudentRegistration> addCourseToRegistration(
        @PathVariable Long registrationId, 
        @PathVariable Long courseId
        ){
        try{
        
            StudentRegistration registration = studentRegistrationService
            .addCourseToRegistration(registrationId, courseId);
            return ResponseEntity.status(HttpStatus.CREATED).body(registration);
                }catch(IllegalArgumentException e){
                    return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }


    @DeleteMapping("{registrationId}/removecourse/{courseId}")
    public void removeCourseRegistration(
        @PathVariable Long registrationId,
         @PathVariable Long courseId
         ){
        try{
            studentRegistrationService.removeCourseRegistration(registrationId, courseId);
        }catch(IllegalArgumentException e){
            ResponseEntity.status(HttpStatus.OK).build();
        }
    }

}
