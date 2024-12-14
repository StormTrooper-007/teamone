package com.edu.teamone.backendapp.repositories;

import com.edu.teamone.backendapp.models.CourseDetails;
import com.edu.teamone.backendapp.security.AppUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseDetailsRepository
        extends JpaRepository<CourseDetails, Long> {
                
}
