package com.example.frisoerprojektbackend.controller;

import com.example.frisoerprojektbackend.model.UserProfile;
import com.example.frisoerprojektbackend.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LoginController {

  @Autowired
  UserProfileService userProfileService;

  @GetMapping("/login/{email}/{password}")
  public UserProfile getLogin(@PathVariable String email, @PathVariable String password) {
   return userProfileService.findUserProfileByEmail(email, password);
  }

}
