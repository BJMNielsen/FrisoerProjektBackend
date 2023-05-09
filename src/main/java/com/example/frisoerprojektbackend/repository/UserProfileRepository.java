package com.example.frisoerprojektbackend.repository;

import com.example.frisoerprojektbackend.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Integer> {

    public boolean existsUserProfileByEmail(String email);

    public UserProfile findUserProfileByEmail(String email);
    public boolean existsUserProfileByPassword(String password);
}
