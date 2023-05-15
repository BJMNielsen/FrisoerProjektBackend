package com.example.frisoerprojektbackend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminProfil {

  private String name;
  private String password;

  private static final AdminProfil adminProfil = new AdminProfil();

  private AdminProfil() {
    name = "Nanna";
    password = "Nanna123";
  }

  public static AdminProfil getNanna() {
    return adminProfil;
  }

}
