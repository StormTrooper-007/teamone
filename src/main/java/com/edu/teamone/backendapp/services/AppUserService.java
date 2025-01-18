package com.edu.teamone.backendapp.services;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.teamone.backendapp.exceptions.UserNotFoundException;
import com.edu.teamone.backendapp.repositories.AppUserRepository;
import com.edu.teamone.backendapp.security.AppUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AppUserService {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    public AppUser registerNewUser(String username, String email, String password, String role) {
        if (appUserRepository.findByUsername(username).isPresent() && appUserRepository.findUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User already Exists");
        }
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        return appUserRepository.save(user);
    }

    public List<AppUser> getUsers(){
       return  appUserRepository.findAll();
    }

    public String getCurrentUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public AppUser getCurrAppUserId() throws UserNotFoundException{
        return appUserRepository.findByUsername(getCurrentUserName())
        .orElseThrow(() -> new UserNotFoundException("user not logged in"));
    }
    
}

