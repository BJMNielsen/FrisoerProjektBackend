package com.example.frisoerprojektbackend.repository;

import com.example.frisoerprojektbackend.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {
}
