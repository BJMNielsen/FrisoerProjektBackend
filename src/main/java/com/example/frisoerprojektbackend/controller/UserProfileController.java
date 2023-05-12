package com.example.frisoerprojektbackend.controller;

import com.example.frisoerprojektbackend.model.UserProfile;
import com.example.frisoerprojektbackend.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/userprofiles")
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @GetMapping("/userprofile/{id}")
    public UserProfile getUserProfileById(@PathVariable int id) {
        return userProfileService.getUserProfileById(id);
    }

    @PostMapping("/userprofile")
    public ResponseEntity<UserProfile> addUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.addUserProfile(userProfile);
    }

    @PutMapping("/userprofile")
    public ResponseEntity<UserProfile> updateUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.updateUserProfile(userProfile);
    }

    @DeleteMapping("/userprofile")
    public ResponseEntity<UserProfile> deleteUserProfile(@RequestBody UserProfile userProfile) {
        return userProfileService.deleteUserProfile(userProfile);
    }
}