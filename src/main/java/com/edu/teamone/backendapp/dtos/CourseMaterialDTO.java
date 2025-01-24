package com.edu.teamone.backendapp.dtos;

import com.edu.teamone.backendapp.models.CourseDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseMaterialDTO {
    private Long id;
    private String title;
    private String url;
    private Long userId;
}
