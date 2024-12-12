package com.edu.teamone.backendapp.security;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.teamone.backendapp.services.AppUserService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUp signUpRequest) {
        try {
            appUserService.signUp(signUpRequest.username(), signUpRequest.email(), signUpRequest.password());
            return ResponseEntity.ok("New user " + "   " + signUpRequest.username() + "  " + " created successfully");
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Could no register new user");
        }
    }
}
