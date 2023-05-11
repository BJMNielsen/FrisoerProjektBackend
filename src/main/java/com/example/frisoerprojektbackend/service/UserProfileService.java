package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.exception.ResourceAlreadyExistsException;
import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.UserProfile;
import com.example.frisoerprojektbackend.repository.UserProfileRepository;
import org.apache.catalina.User;
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

    public UserProfile getUserProfileById(int id) {
        return userProfileRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Userprofile with id: " + id + " does not exist and could therefore not find any userprofile."));
    }

    public UserProfile getUserProfileByEmail(String email){
        return userProfileRepository.findUserProfileByEmail(email).orElseThrow(()-> new ResourceNotFoundException("There is no userprofile with the email: " + email + " and therefore could not get a profile"));
    }

    public UserProfile findUserProfileByEmail(String email, String password){
        UserProfile userProfile = getUserProfileByEmail(email);

        if (userProfile.getPassword().equals(password)){
            return userProfile;
        }

        throw new ResourceNotFoundException("The password for the user with the email: " + email + " was incorrect");
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

    public ResponseEntity<UserProfile> updateUserProfile(UserProfile userProfile) {
        boolean exist = userProfileRepository.existsById(userProfile.getId());
        if(!exist) {
            throw new ResourceNotFoundException("Userprofile with id: " + userProfile.getId() + " could not be found and therefore could not be updated");
        }
        // Her tjekker vi om den nye email de gerne vil have allerede eksistere. Den skal være unik
        boolean emailExists = userProfileRepository.existsUserProfileByEmail(userProfile.getEmail());

        // Vi finder den nuværende profil i databasen og dens id som den ser ud nu i tabellen, ud fra det overførste userProfiles id.
        UserProfile oldUserProfile = getUserProfileById(userProfile.getId());

        // Her sammenligner vi den gamle profils email med det nye overførste Json objekts email(den opdaterede profil vi får tilsendt fra frontenden, dvs userProfile)
        boolean sameEmailAsBefore = oldUserProfile.getEmail().equals(userProfile.getEmail());

        // Hvis emailen allerede eksistere, og det ikke er den samme email som du havde før, så thrower vi en exception og user får ikke lov til at update.
        if (emailExists && !sameEmailAsBefore) {
            // Tjekker om der allerede eksistere den email brugeren har skrevet. Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Userprofile with email: " + userProfile.getEmail() + " already exists, so you cannot use that email");
        }
        // Hvis emailen existere og den er den samme som du tidligere havde, så får du lov til at gemme objektet.
        UserProfile updatedUserProfile = userProfileRepository.save(userProfile);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }

    public ResponseEntity<UserProfile> deleteUserProfile(UserProfile userProfile) {
        boolean exists = userProfileRepository.existsById(userProfile.getId());
        if(!exists){
            throw new ResourceNotFoundException("Userprofile with id: " + userProfile.getId() + " could not be found and therefore could not be deleted");
        }
        userProfileRepository.deleteById(userProfile.getId());
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
