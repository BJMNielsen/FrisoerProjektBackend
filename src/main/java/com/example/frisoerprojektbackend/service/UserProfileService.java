package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.exception.ResourceAlreadyExistsException;
import com.example.frisoerprojektbackend.model.UserProfile;
import com.example.frisoerprojektbackend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    public List<UserProfile> getUserProfiles() {
        return userProfileRepository.findAll();
    }

    public ResponseEntity<UserProfile> addUserProfile(UserProfile userProfile) {
        boolean exists = userProfileRepository.existsById(userProfile.getId());
        if (exists) {
            // Tjekker om der allerede eksistere en userprofile med et ID. Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Userprofile with id: " + userProfile.getId() + " already exists and therefore can't be added.");
        }

        boolean emailExists = userProfileRepository.existsUserProfileByEmail(userProfile.getEmail());
        if (emailExists) {
            // Tjekker om der allerede eksistere den email brugeren har skrevet. Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Userprofile with email: " + userProfile.getEmail() + " already exists");
        }

        else {
            // Hvis den ikke existere, og email ikke eksistere, så laver vi en ny userprofile og gemmer i databasen.
            UserProfile newUserProfile = userProfileRepository.save(userProfile);
            return new ResponseEntity<>(newUserProfile, HttpStatus.OK);
        }
    }

  /*  public ResponseEntity<UserProfile> deleteUserProfile(UserProfile userProfile) {
        boolean notExists = !userProfileRepository.existsById(userProfile.getId());
        if(notExists){

        }
        return;
    }*/


}
