package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.AdminProfil;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  public AdminProfil loginInAsAdmin(String givenName, String givenPassword) {
    AdminProfil nannasProfil = AdminProfil.getNanna();
    String adminName = nannasProfil.getName();
    String adminPassword = nannasProfil.getPassword();

    boolean isNameIncorrect = !adminName.equals(givenName);
    boolean isPasswordIncorrect = !adminPassword.equals(givenPassword);

    if (isNameIncorrect) {
      throw new ResourceNotFoundException("The given name: " + givenName + " was incorrect!");
    }
    if (isPasswordIncorrect) {
      throw new ResourceNotFoundException("The given password: " + givenPassword + " was incorrect!");
    }

    return nannasProfil;
  }

}
