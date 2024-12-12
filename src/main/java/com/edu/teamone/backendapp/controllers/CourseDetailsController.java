package com.edu.teamone.backendapp.controllers;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.services.CourseDetailsService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseDetailsController {

    private final CourseDetailsService courseDetailsService;


    @PostMapping
    public ResponseEntity<CourseDetails> addNewCourse(@RequestBody CourseDetails courseDetails){
        try {
            courseDetailsService.addCourse(courseDetails);

        } catch (IllegalArgumentException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(courseDetails);
    }

    @GetMapping
    public ResponseEntity<List<CourseDetails>> getCourses(){
        List<CourseDetails> courses = courseDetailsService.getAllCourses();
        return ResponseEntity.ok(courses);
    }


}
