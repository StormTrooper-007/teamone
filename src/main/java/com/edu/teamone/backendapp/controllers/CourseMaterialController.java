package com.edu.teamone.backendapp.controllers;

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

import com.edu.teamone.backendapp.models.CourseMaterial;
import com.edu.teamone.backendapp.services.CourseMaterialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/materials")
@RequiredArgsConstructor
public class CourseMaterialController {

    private final CourseMaterialService courseMaterialService;

    @PostMapping("/{username}")
    public ResponseEntity<CourseMaterial> addNewCourseMaterial(
        @PathVariable String username, @RequestBody CourseMaterial courseMaterial) {
        try {
            courseMaterialService.addCourseMaterial(username, courseMaterial);
            return ResponseEntity.status(HttpStatus.CREATED).body(courseMaterial);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    
    @GetMapping
    public ResponseEntity<List<CourseMaterial>> getMaterials() {
        try {
            List<CourseMaterial> materials = courseMaterialService.getAllCourseMaterial();
            return ResponseEntity.ok(materials);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseMaterial> editCourse(@PathVariable Long id, @RequestBody CourseMaterial update) {
        try {
            CourseMaterial courseMaterial = courseMaterialService.editCourseMaterial(id, update);
            return ResponseEntity.ok(courseMaterial);

        } catch (NoSuchElementException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id) {
        try {
            courseMaterialService.deleteCourseMaterial(id);
        } catch (NoSuchElementException e) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
