package com.edu.teamone.backendapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<Assignment> assignments = assignmentService.getAllAssignments(userId);
        return ResponseEntity.ok(assignments);
    }

    @PostMapping("/{assignmentId}/grade")
    public ResponseEntity<Assignment> gradeAssignment(
            @PathVariable Long assignmentId,
            @RequestParam String grade,
            @RequestParam String feedback,
            @RequestParam Long lecturerId) {
        Assignment gradedAssignment = assignmentService.gradeAssignment(assignmentId, grade, feedback, lecturerId);
        return ResponseEntity.ok(gradedAssignment);
    }

}
