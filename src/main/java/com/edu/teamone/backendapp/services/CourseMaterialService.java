package com.edu.teamone.backendapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.models.CourseMaterial;
import com.edu.teamone.backendapp.repositories.CourseMaterialRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class CourseMaterialService {

    private final CourseMaterialRepository courseMaterialRepository;

    public CourseMaterial addCourseMaterial(CourseMaterial courseMaterial){
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
