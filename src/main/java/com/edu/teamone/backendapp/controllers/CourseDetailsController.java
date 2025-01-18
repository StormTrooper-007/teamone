package com.edu.teamone.backendapp.controllers;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.services.CourseDetailsService;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseDetailsController {

    private final CourseDetailsService courseDetailsService;

    @PostMapping("/{username}")
    public ResponseEntity<CourseDetails> addNewCourse(@PathVariable String username, @RequestBody CourseDetails courseDetails) {
        try {
          courseDetailsService.addCourse(username, courseDetails);

        } catch (Exception e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return null;
    }

    @GetMapping
    public ResponseEntity<List<CourseDetails>> getCourses() {
        try {
            List<CourseDetails> courses = courseDetailsService.getAllCourses();
            return ResponseEntity.ok(courses);

        } catch (NoSuchElementException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseDetails> editCourse(@PathVariable Long id, @RequestBody CourseDetails update) {
        try {
            CourseDetails courseDetails = courseDetailsService.editCourse(id, update);
            return ResponseEntity.ok(courseDetails);

        } catch (NoSuchElementException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        try {
            courseDetailsService.deleteCourse(id);
        } catch (NoSuchElementException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
