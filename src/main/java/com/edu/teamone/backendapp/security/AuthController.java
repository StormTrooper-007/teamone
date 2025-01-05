package com.edu.teamone.backendapp.security;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.teamone.backendapp.services.AppUserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<String> registerForm(@RequestBody AppUser appUser) {
        try {
            appUserService.registerNewUser(appUser.getUsername(), appUser.getEmail(), appUser.getPassword(), appUser.getRole());
            return ResponseEntity.ok("New user " + "   " + appUser.getUsername() + "  " + " created successfully");
        } catch (IllegalArgumentException ex) {
         throw new IllegalArgumentException("Could no register new user");
        }
    }

    @GetMapping("/users")
    ResponseEntity<List<AppUser>> getUsers(){
        List<AppUser> res = appUserService.getUsers();
        return ResponseEntity.ok(res);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.status(HttpStatus.OK).body(SecurityContextHolder.getContext().getAuthentication().getName());

    }

    @GetMapping("/user")
        public String getCurrentUser() {
            return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getName());
    }

}
