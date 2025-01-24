package com.edu.teamone.backendapp.dtos;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.security.AppUser;

public class AssignmentDTO {
    private Long id;
    private String title;
    private String submissionUrl;
    private String feedback;
    private String grade;
    private Long userId;
    private Long courseId;
    private Long lecturerId;
    private CourseDetails courseName;
}
