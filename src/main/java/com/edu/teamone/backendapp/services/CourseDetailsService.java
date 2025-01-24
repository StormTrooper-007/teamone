package com.edu.teamone.backendapp.services;


import com.edu.teamone.backendapp.dtos.CourseDetailsDTO;
import com.edu.teamone.backendapp.dtos.CourseMaterialDTO;
import com.edu.teamone.backendapp.exceptions.UnauthrorizedRoleException;
import com.edu.teamone.backendapp.exceptions.UserNotFoundException;
import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.repositories.AppUserRepository;
import com.edu.teamone.backendapp.repositories.CourseDetailsRepository;
import com.edu.teamone.backendapp.security.AppUser;

import lombok.RequiredArgsConstructor;



import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;



@Service
@RequiredArgsConstructor
public class CourseDetailsService{

    private final CourseDetailsRepository courseDetailsRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

     private CourseDetailsDTO mapToCourseDetailsDTO(CourseDetails course) {
        List<CourseMaterialDTO> materials = course.getCourseMaterials()
                .stream()
                .map(material -> new CourseMaterialDTO(
                        material.getId(),
                        material.getTitle(),
                        material.getUrl(),
                        material.getUserId()))
                .collect(Collectors.toList());

        return new CourseDetailsDTO(
                course.getId(),
                course.getCourseName(),
                course.getDescription(),
                course.getUserId(),
                materials);
    }

              

    public CourseDetails addCourse(String username, CourseDetails newCourse) throws UserNotFoundException, UnauthrorizedRoleException{
         AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (!"LECTURER".equalsIgnoreCase(user.getRole())) {
            throw new UnauthrorizedRoleException("Only users with role 'LECTURER' can create courses");
        }

        try{
            newCourse.setUserId(appUserService.getCurrAppUserId().getId());
        }catch(UserNotFoundException e){
            throw new UserNotFoundException("user not logged in");
        }

        courseDetailsRepository.save(newCourse);
        return newCourse;
    }

    
     public List<CourseDetailsDTO> getAllCourses() {
        List<CourseDetails> courses = courseDetailsRepository.findAll();
        return courses.stream()
                      .map(this::mapToCourseDetailsDTO)
                      .collect(Collectors.toList());
    }
        

    
    public CourseDetails editCourse(Long id, CourseDetails update) {
        CourseDetails toEdit = courseDetailsRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("could not find course"));
        toEdit.setCourseName(update.getCourseName());
        toEdit.setDescription(update.getDescription());

        courseDetailsRepository.save(toEdit);

        return toEdit;
    }

    public void deleteCourse(Long id) {
        courseDetailsRepository.deleteById(id);
    }
    
}
