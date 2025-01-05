package com.edu.teamone.backendapp.services;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.repositories.AppUserRepository;
import com.edu.teamone.backendapp.repositories.CourseDetailsRepository;
import com.edu.teamone.backendapp.security.AppUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
public class CourseDetailsService{

    private final CourseDetailsRepository courseDetailsRepository;
    private final AppUserRepository appUserRepository;

    public CourseDetails addCourse(String username, CourseDetails newCourse){
         AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!"LECTURER".equalsIgnoreCase(user.getRole())) {
            throw new IllegalArgumentException("Only users with role 'LECTURER' can add courses");
        }

        courseDetailsRepository.save(newCourse);
        return newCourse;
    }

    
    public List<CourseDetails> getAllCourses() {
        return courseDetailsRepository.findAll();
    }

    
    public CourseDetails editCourse(Long id, CourseDetails update) {
        CourseDetails toEdit = courseDetailsRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        toEdit.setCourseName(update.getCourseName());
        toEdit.setDescription(update.getDescription());

        courseDetailsRepository.save(toEdit);

        return toEdit;
    }

    public void deleteCourse(Long id) {
        courseDetailsRepository.deleteById(id);
    }
    
}
