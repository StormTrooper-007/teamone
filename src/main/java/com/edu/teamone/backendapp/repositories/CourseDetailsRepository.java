package com.edu.teamone.backendapp.repositories;

import com.edu.teamone.backendapp.models.CourseDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDetailsRepository
        extends JpaRepository<CourseDetails, Long> {
}
