package com.edu.teamone.backendapp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.teamone.backendapp.models.Assignment;
import com.edu.teamone.backendapp.services.AssignmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping("{userId}")
    public ResponseEntity<List<Assignment>> getAllAssignments(@PathVariable Long userId) {
        try {
            List<Assignment> assignments = assignmentService.getAllAssignments(userId);
            return ResponseEntity.ok(assignments);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @PostMapping("/{assignmentId}/{lecturerId}/grade")
    public ResponseEntity<Assignment> gradeAssignment(
            @PathVariable Long assignmentId,
            @RequestBody Assignment assignment,
            @PathVariable Long lecturerId) {
        try {
            Assignment gradedAssignment = assignmentService.gradeAssignment(assignmentId, assignment, lecturerId);
            return ResponseEntity.ok(gradedAssignment);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
