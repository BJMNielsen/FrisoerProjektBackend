package com.example.frisoerprojektbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private int Id;

    private LocalDate Date;

    private double Price;

    // Her laver vi en foreign key column i vores booking tabel med navnet "user_profile_id", der referere til den column i
    // user_profile tabellen som hedder user_profile_id. Dvs booking tabellen får en ny kolonne med navnet "user_profile_id",
    // der indeholder alle værdierne fra kolonnen user_profile_id i tabellen user_profile.

    @ManyToOne // Many Bookings til én UserProfile
    @JoinColumn(name = "user_profile_id", referencedColumnName = "user_profile_id")
    private UserProfile bookingUserProfile;


    @ManyToOne // Many Bookings til én TimeSlot
    @JoinColumn(name = "time_slot_id", referencedColumnName = "time_slot_id") // Her kalder vi vores foreign key column "time_slot_id", og værdierne den lægger ind sætter vi til at være dem der findes i referenceColumnName = "time_slot_id" variablen inde fra TimeSlot klassen.
    private TimeSlot timeSlot;

    // One Booking to many BookingTreatments
    @OneToMany(mappedBy = "booking") // Her referere vi til booking variablen inde i BookingTreatments klassen.
    @JsonBackReference
    private List<BookingTreatments> bookingTreatmentsList;



}
