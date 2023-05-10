package com.example.frisoerprojektbackend.repository;

import com.example.frisoerprojektbackend.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    boolean existsUserProfileByEmail(String email);

    Optional<UserProfile> findUserProfileByEmail(String email);
}
