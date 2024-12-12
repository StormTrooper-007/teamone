package com.edu.teamone.backendapp.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.models.AppUser;
import com.edu.teamone.backendapp.repositories.AppUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    
     public String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public AppUser signUp(String username, String email, String password) throws IllegalArgumentException {
        if (appUserRepository.findByUsername(username).isPresent() && appUserRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User already Exists");
        }

        AppUser newUser = new AppUser();
        newUser.setLastName(username);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));

        return appUserRepository.save(newUser);
    }
}

