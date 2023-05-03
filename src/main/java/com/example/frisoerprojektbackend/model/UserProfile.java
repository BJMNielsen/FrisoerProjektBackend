package com.example.frisoerprojektbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false) // Not null og unique
    private int profileId;

    @Column(nullable = false) // Not null
    private String name;

    @Column(unique = true, nullable = false) // Not null og unique
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false) // Not null
    private String password;

}
