package com.edu.teamone.backendapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.models.Assignment;
import com.edu.teamone.backendapp.repositories.AppUserRepository;
import com.edu.teamone.backendapp.repositories.AssignmentRepository;
import com.edu.teamone.backendapp.security.AppUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AppUserRepository appUserRepository;

    public List<Assignment> getAllAssignments(Long userId) {
        AppUser user = appUserRepository.findById(userId)
        .orElseThrow(() -> (new IllegalArgumentException("user not found")));
        if(user.getRole().equalsIgnoreCase("lecturer")){
            return assignmentRepository.findAll();
        }else{
            throw new RuntimeException("only users with role 'lecturer' can view assignments");
        }
    }

    public Assignment gradeAssignment(Long assignmentId, String grade, String feedback, Long lecturerId){

        Assignment assignment = assignmentRepository.findById(assignmentId)
        .orElseThrow(() -> new IllegalArgumentException("Assignment not found"));

        AppUser lecturer = appUserRepository.findById(lecturerId)
        .orElseThrow(() -> new IllegalArgumentException("Lecturer not found"));

        if (!lecturer.getRole().equalsIgnoreCase("lecturer")) {
            throw new IllegalArgumentException("User is not authorized to grade assignments");
        }

        assignment.setGrade(grade);
        assignment.setFeedback(feedback);
        assignment.setLecturer(lecturer);

        return assignmentRepository.save(assignment);

    }

}
