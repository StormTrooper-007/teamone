package com.edu.teamone.backendapp.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRegistrationDTO {
    private Long id;
    private Long appUserId;
    private List<CourseDetailsDTO> coursesRegistered;
}
