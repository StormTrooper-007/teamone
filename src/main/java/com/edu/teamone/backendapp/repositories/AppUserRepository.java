package com.edu.teamone.backendapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.teamone.backendapp.models.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    Optional<AppUser> findByUsername(String username);
    Optional<AppUser> findByEmail(String email);
}
