package com.edu.teamone.backendapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

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

    public StudentRegistration registerForCourse(String username, Long courseId) {
        AppUser user = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!(user.getRole().equalsIgnoreCase("student"))) {
            throw new IllegalArgumentException("only users with role 'student' can register for courses");
        }

        CourseDetails course = courseDetailsRepository
                .findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("course not found"));

        StudentRegistration studentRegistration = studentRegistrationRepository
                .findById(user.getId())
                .orElseGet(() -> {
                    StudentRegistration newStudent = new StudentRegistration();
                    newStudent.setId(user.getId());
                    newStudent.setAssignments(new ArrayList<>());
                    newStudent.setCoursesRegistered(new ArrayList<>());
                    return studentRegistrationRepository.save(newStudent);
                });

        if (!studentRegistration.getCoursesRegistered().contains(course)) {
            studentRegistration.getCoursesRegistered().add(course);
        } else {
            throw new IllegalStateException("Student is already registered for this course");
        }
        return studentRegistrationRepository.save(studentRegistration);

    }

    public List<StudentRegistration> getRegisteredStudent() {
        try {
            List<StudentRegistration> students = studentRegistrationRepository.findAll();
            return students;
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException();
        }
    }

    public StudentRegistration addCourseToRegistration(Long registrationId, Long courseId) {
        StudentRegistration registration = studentRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("registration not found"));

        CourseDetails course = courseDetailsRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
        if (!registration.getCoursesRegistered().contains(course)) {
            registration.getCoursesRegistered().add(course);

        } else {
            throw new IllegalStateException("Student is already registered for this course");
        }

        return studentRegistrationRepository.save(registration);
    }

    public StudentRegistration removeCourseRegistration(Long registrationId, Long courseId) {
        StudentRegistration registration = studentRegistrationRepository.findById(registrationId)
                .orElseThrow(() -> new IllegalArgumentException("Student registration not found"));

        CourseDetails course = courseDetailsRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

        if (registration.getCoursesRegistered().contains(course)) {
            registration.getCoursesRegistered().remove(course);
        } else {
            throw new IllegalStateException("Course is not registered for this student");
        }

        return studentRegistrationRepository.save(registration);

    }

}
