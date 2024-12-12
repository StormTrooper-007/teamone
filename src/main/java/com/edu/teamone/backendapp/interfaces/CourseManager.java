package com.edu.teamone.backendapp.interfaces;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.models.CourseMaterial;

import java.util.List;

public interface CourseManager {
    public CourseDetails addCourse(CourseDetails newCourse);
    public List<CourseDetails> getAllCourses();
    public CourseDetails editCourse(Long id, CourseDetails update);
    public void deleteCourse(Long id);
    //course material
    public List<CourseMaterial> addCourseMaterial(Long courseId, CourseMaterial courseMaterial);
    public List<CourseMaterial> getCourseMaterials(CourseDetails courseDetails);
    public void editCourseMaterial(Long courseId, Long materialId, CourseMaterial courseMaterial);
    public void deleteCourseMaterial(Long courseDetailId, CourseMaterial courseMaterial);

    //assignment
    



}
