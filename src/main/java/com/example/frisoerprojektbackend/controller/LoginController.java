package com.example.frisoerprojektbackend.controller;

import com.example.frisoerprojektbackend.model.AdminProfil;
import com.example.frisoerprojektbackend.model.UserProfile;
import com.example.frisoerprojektbackend.service.AdminService;
import com.example.frisoerprojektbackend.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class LoginController {

  @Autowired
  UserProfileService userProfileService;

  @Autowired
  AdminService adminService;

  @GetMapping("/login/{email}/{password}")
  public UserProfile getLogin(@PathVariable String email, @PathVariable String password) {
   return userProfileService.findUserProfileByEmail(email, password);
  }

  @GetMapping("/login/admin/{name}/{password}")
  public AdminProfil getAdminLogin(@PathVariable String name, @PathVariable String password) {
    return adminService.loginInAsAdmin(name, password);
  }

}
