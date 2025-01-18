package com.edu.teamone.backendapp.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.dtos.CourseDetailsDTO;
import com.edu.teamone.backendapp.dtos.StudentRegistrationDTO;
import com.edu.teamone.backendapp.exceptions.UnauthrorizedRoleException;
import com.edu.teamone.backendapp.exceptions.UserNotFoundException;
import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.models.StudentRegistration;
import com.edu.teamone.backendapp.repositories.AppUserRepository;
import com.edu.teamone.backendapp.repositories.CourseDetailsRepository;
import com.edu.teamone.backendapp.repositories.StudentRegistrationRepository;
import com.edu.teamone.backendapp.security.AppUser;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentRegistrationService {

    private final AppUserRepository appUserRepository;
    private final CourseDetailsRepository courseDetailsRepository;
    private final StudentRegistrationRepository studentRegistrationRepository;

    private StudentRegistrationDTO mapToStudentRegistrationDTO(StudentRegistration registration) {
        // Map coursesRegistered from entities to DTOs
        List<CourseDetailsDTO> courses = registration.getCoursesRegistered().stream()
                .map(course -> new CourseDetailsDTO(
                        course.getId(),
                        course.getCourseName(),
                        course.getDescription()))
                .collect(Collectors.toList());

        // Map StudentRegistration fields to StudentRegistrationDTO
        return new StudentRegistrationDTO(
                registration.getId(),
                registration.getStudent().getUsername(),
                courses);
    }

    public StudentRegistrationDTO registerForCourse(String username, Long courseId) throws UnauthrorizedRoleException, UserNotFoundException{
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new NoSuchElementException("User not logged in"));

        if (!"STUDENT".equalsIgnoreCase(user.getRole())) {
            throw new UnauthrorizedRoleException("only users with role 'student' can register for courses");
        }

        CourseDetails course = courseDetailsRepository
                .findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("course not found"));

        StudentRegistration registration = user.getStudentRegistration();

        if (registration == null) {
            registration = new StudentRegistration();
            registration.setStudent(user);
            user.setStudentRegistration(registration);
        }

        if (registration.getCoursesRegistered() == null) {
            registration.setCoursesRegistered(new ArrayList<>());
        }

        if (!registration.getCoursesRegistered().contains(course)) {
            registration.getCoursesRegistered().add(course);
        }
        try{
            registration.setAppUserId(user.getId());
        }catch(Exception e){
            throw new UserNotFoundException("user no logged in");
        }

        studentRegistrationRepository.save(registration);
        return mapToStudentRegistrationDTO(registration);
    }

    public List<StudentRegistrationDTO> getRegisteredStudents() {
        List<StudentRegistration> students = studentRegistrationRepository.findAll();
        return students.stream()
                .map(this::mapToStudentRegistrationDTO)
                .collect(Collectors.toList());
    }


    public StudentRegistrationDTO addCourseToRegistration(Long registrationId, Long courseId) {
        StudentRegistration registration = studentRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Registration not found"));

        CourseDetails course = courseDetailsRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (!registration.getCoursesRegistered().contains(course)) {
            registration.getCoursesRegistered().add(course);
        } else {
            throw new NoSuchElementException("Student is already registered for this course");
        }

        studentRegistrationRepository.save(registration);

        return mapToStudentRegistrationDTO(registration);
    }

    public StudentRegistrationDTO removeCourseRegistration(Long registrationId, Long courseId) {
        StudentRegistration registration = studentRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new NoSuchElementException("Student registration not found"));

        CourseDetails course = courseDetailsRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));

        if (registration.getCoursesRegistered().contains(course)) {
            registration.getCoursesRegistered().remove(course);
        } else {
            throw new IllegalStateException("Course is not registered for this student");
        }

        studentRegistrationRepository.save(registration);

        return mapToStudentRegistrationDTO(registration);
    }

}
