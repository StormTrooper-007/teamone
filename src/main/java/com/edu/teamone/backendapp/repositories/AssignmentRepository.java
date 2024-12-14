package com.edu.teamone.backendapp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.edu.teamone.backendapp.models.Assignment;



@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
   
    
}
