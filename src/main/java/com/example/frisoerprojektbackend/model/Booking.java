package com.example.frisoerprojektbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int Id;

    // Her laver vi en foreign key column i vores booking tabel med navnet "user_profile_id", der referere til den column i
    // user_profile tabellen som hedder user_profile_id. Dvs booking tabellen får en ny kolonne med navnet "user_profile_id",
    // der indeholder alle værdierne fra kolonnen user_profile_id i tabellen user_profile.
    @JoinColumn(name = "user_profile_id", referencedColumnName = "user_profile_id")
    private UserProfile userProfile;


    private TimeSlot timeSlot;
    private LocalDate Date;
    private double Price;
}
