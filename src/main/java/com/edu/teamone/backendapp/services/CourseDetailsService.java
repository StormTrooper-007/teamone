package com.edu.teamone.backendapp.services;

import com.edu.teamone.backendapp.interfaces.CourseManager;
import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.repositories.CourseDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class CourseDetailsService implements CourseManager {

    private final CourseDetailsRepository courseDetailsRepository;

    @Override
    public CourseDetails addCourse(CourseDetails newCourse){
        courseDetailsRepository.save(newCourse);
        return newCourse;
    }

    @Override
    public List<CourseDetails> getAllCourses(){
        return courseDetailsRepository.findAll();
    }

    @Override
    public CourseDetails editCourse(Long id, CourseDetails update){
        CourseDetails toEdit = courseDetailsRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        toEdit.setCourseName(update.getCourseName());
        toEdit.setDescription(update.getDescription());

        courseDetailsRepository.save(toEdit);

        return toEdit;
    }

    @Override
    public void deleteCourse(Long id){
         courseDetailsRepository.deleteById(id);
    }}
