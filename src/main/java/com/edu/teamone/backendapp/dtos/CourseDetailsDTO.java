package com.edu.teamone.backendapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailsDTO {
    private Long id;
    private String courseName;
    private String description;
}
