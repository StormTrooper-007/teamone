package com.edu.teamone.backendapp.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    
    private String firstName;
    private String lastName;
    private String username = firstName + "." + lastName;
    private String email;
    private String password;
    private String profilePictureUrl;

    @Enumerated(EnumType.STRING)
    private Role role;

}
