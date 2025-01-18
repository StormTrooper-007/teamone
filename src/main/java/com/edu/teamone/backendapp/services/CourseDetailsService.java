package com.edu.teamone.backendapp.services;

import com.edu.teamone.backendapp.exceptions.UnauthrorizedRoleException;
import com.edu.teamone.backendapp.exceptions.UserNotFoundException;
import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.repositories.AppUserRepository;
import com.edu.teamone.backendapp.repositories.CourseDetailsRepository;
import com.edu.teamone.backendapp.security.AppUser;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;



@Service
@RequiredArgsConstructor
public class CourseDetailsService{

    private final CourseDetailsRepository courseDetailsRepository;
    private final AppUserRepository appUserRepository;
    private final AppUserService appUserService;

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

    
    public List<CourseDetails> getAllCourses() {
        return courseDetailsRepository.findAll();
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
