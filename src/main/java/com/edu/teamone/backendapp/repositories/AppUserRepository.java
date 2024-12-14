package com.edu.teamone.backendapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edu.teamone.backendapp.security.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long>{
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findUserByEmail(String email);
    
}
