package com.example.frisoerprojektbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Treatment {

    @Id
    @Column(name = "treatment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double Price;

    @Column(nullable = false, unique = true)
    private String Name;

    // One Treatment to many bookingTreatments.
    @OneToMany(mappedBy = "treatment") // Her referere vi til treatment variablen inde i BookingTreatments klassen.
    @JsonBackReference
    private List<BookingTreatments> ListOfBookingTreatments;

}
