package com.edu.teamone.backendapp.dtos;

import java.util.List;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.models.CourseMaterial;

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
    private Long userId;
    private List<CourseMaterialDTO> courseMaterials;
}
