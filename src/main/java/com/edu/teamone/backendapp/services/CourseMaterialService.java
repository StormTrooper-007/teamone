package com.edu.teamone.backendapp.services;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.models.CourseMaterial;
import com.edu.teamone.backendapp.repositories.AppUserRepository;
import com.edu.teamone.backendapp.repositories.CourseMaterialRepository;
import com.edu.teamone.backendapp.security.AppUser;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class CourseMaterialService {

    private final CourseMaterialRepository courseMaterialRepository;

    private final AppUserRepository appUserRepository;

    public CourseMaterial addCourseMaterial(CourseMaterial courseMaterial){
        String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        AppUser user = appUserRepository.findByUsername(currentUser)
        .orElseThrow(() -> new IllegalArgumentException());
        courseMaterialRepository.save(courseMaterial);
        return courseMaterial;
    }

    public List<CourseMaterial> getAllCourseMaterial(){
        return courseMaterialRepository.findAll();
    }

    public CourseMaterial editCourseMaterial(Long id, CourseMaterial update){
        CourseMaterial toEdit = courseMaterialRepository.findById(id)
                .orElseThrow(RuntimeException::new);
        toEdit.setTitle(update.getTitle());
        toEdit.setUrl(update.getUrl());

        courseMaterialRepository.save(toEdit);
        return toEdit;
    }

    public void deleteCourseMaterial(Long id){
         courseMaterialRepository.deleteById(id);
    }
}
