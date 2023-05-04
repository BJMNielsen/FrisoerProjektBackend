package com.example.frisoerprojektbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int Id;

    @Column(nullable = false)
    private LocalDate Date;

    @Column(nullable = false)
    private double Price;

    // Her laver vi en foreign key column i vores booking tabel med navnet "user_profile_id", der referere til den column i
    // user_profile tabellen som hedder user_profile_id. Dvs booking tabellen får en ny kolonne med navnet "user_profile_id",
    // der indeholder alle værdierne fra kolonnen user_profile_id i tabellen user_profile.

    @Column(nullable = false)
    @ManyToOne // Many bookings til én user profile
    @JoinColumn(name = "user_profile_id", referencedColumnName = "user_profile_id")
    private UserProfile bookingUserProfile;

    @Column(nullable = false)
    @ManyToOne // Many bookings til én timeslot
    private TimeSlot timeSlot;

    @Column(nullable = false)
    @OneToMany(mappedBy = "booking") // Her referere vi til booking variablen inde i BookingTreatments klassen.
    @JsonBackReference
    private List<BookingTreatments> bookingTreatmentsList;



}
