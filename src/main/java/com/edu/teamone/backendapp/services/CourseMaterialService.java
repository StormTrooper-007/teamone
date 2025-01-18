package com.edu.teamone.backendapp.services;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.exceptions.UnauthrorizedRoleException;
import com.edu.teamone.backendapp.exceptions.UserNotFoundException;
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
    private final AppUserService appUserService;

    public CourseMaterial addCourseMaterial(String username, CourseMaterial courseMaterial) throws UnauthrorizedRoleException, UserNotFoundException{
        AppUser user = appUserRepository.findByUsername(username)
        .orElseThrow(() -> new NoSuchElementException("user not logged in"));

        if (!"LECTURER".equalsIgnoreCase(user.getRole())) {
            throw new UnauthrorizedRoleException("Only users with role 'LECTURER' can create courses");
        }

          try{
            courseMaterial.setUserId(appUserService.getCurrAppUserId().getId());
        }catch(UserNotFoundException e){
            throw new UserNotFoundException("user not logged in");
        }
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
