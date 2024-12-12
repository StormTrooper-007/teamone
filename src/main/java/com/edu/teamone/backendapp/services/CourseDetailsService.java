package com.edu.teamone.backendapp.services;

import com.edu.teamone.backendapp.interfaces.CourseManager;
import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.models.CourseMaterial;
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
    }

    @Override
    public List<CourseMaterial> addCourseMaterial(Long courseId, CourseMaterial courseMaterial){
        CourseDetails specificCourse = courseDetailsRepository
        .findById(courseId)
        .orElseThrow(RuntimeException::new);
        specificCourse.getCourseMaterials().add(courseMaterial);
        return specificCourse.getCourseMaterials();
    }

    @Override
    public List<CourseMaterial> getCourseMaterials(CourseDetails courseDetails){
        return  courseDetails.getCourseMaterials();
    }

    @Override
    public void editCourseMaterial(Long courseId, Long materialId, CourseMaterial courseMaterial){
        CourseDetails specificCourse = courseDetailsRepository.findById(courseId)
        .orElseThrow(RuntimeException::new);
        specificCourse.getCourseMaterials().stream()
        .filter(cm -> cm.getId().equals(materialId))
        .forEach(ele -> {
            ele.setTitle(courseMaterial.getTitle());
            ele.setUrl(courseMaterial.getUrl());
        });
    }

    @Override
    public void deleteCourseMaterial(Long courseDetailId, CourseMaterial courseMaterial){
        CourseDetails specificCourse = courseDetailsRepository
        .findById(courseDetailId)
        .orElseThrow(RuntimeException::new);
        specificCourse.getCourseMaterials()
        .removeIf(cm -> cm.getId().equals(courseMaterial.getId()));
    }
}
