package com.edu.teamone.backendapp.interfaces;

import com.edu.teamone.backendapp.models.CourseDetails;


import java.util.List;

public interface CourseManager {
    public CourseDetails addCourse(CourseDetails newCourse);
    public List<CourseDetails> getAllCourses();
    public CourseDetails editCourse(Long id, CourseDetails update);
    public void deleteCourse(Long id);
    //course material
   

    //assignment




}
