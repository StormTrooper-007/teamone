package com.edu.teamone.backendapp.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.exceptions.UnauthrorizedRoleException;
import com.edu.teamone.backendapp.exceptions.UserNotFoundException;
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
    private final AppUserService appUserService;

    public Object createAssignment(String username, Assignment assignment)
     throws UnauthrorizedRoleException, UserNotFoundException{
         AppUser user = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new NoSuchElementException("user not logged in"));

          if (!"LECTURER".equalsIgnoreCase(user.getRole())) {
            throw new UnauthrorizedRoleException("Only users with role 'LECTURER' can create courses");
        }

          try{
            assignment.setTitle(assignment.getTitle());
            assignment.setSubmissionUrl(assignment.getSubmissionUrl());
            assignment.setFeedback("add feedback");
            assignment.setGrade("add grade");
            assignment.setUserId(appUserService.getCurrAppUserId().getId());
        }catch(UserNotFoundException e){
            throw new UserNotFoundException("user not logged in");
        }

        assignmentRepository.save(assignment);

        return assignment;
    }

    public List<Assignment> getAllAssignments(Long userId) throws UnauthrorizedRoleException{
        AppUser user = appUserRepository.findById(userId)
        .orElseThrow(() -> (new NoSuchElementException("user not found")));
        if(user.getRole().equalsIgnoreCase("lecturer")){
            return assignmentRepository.findAll();
        }else{
            throw new UnauthrorizedRoleException("only users with role 'lecturer' can view assignments");
        }
    }

    public Assignment gradeAssignment(Long assignmentId, Assignment assignmentUdate, Long lecturerId) throws UnauthrorizedRoleException{

        Assignment assignment = assignmentRepository.findById(assignmentId)
        .orElseThrow(() -> new NoSuchElementException("Assignment not found"));

        AppUser lecturer = appUserRepository.findById(lecturerId)
        .orElseThrow(() -> new NoSuchElementException("Lecturer not found"));

        if (!lecturer.getRole().equalsIgnoreCase("lecturer")) {
            throw new UnauthrorizedRoleException("User is not authorized to grade assignments");
        }

        assignment.setGrade(assignment.getGrade());
        assignment.setFeedback(assignment.getFeedback());
        

        return assignmentRepository.save(assignment);

    }

}
