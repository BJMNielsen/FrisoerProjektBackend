package com.example.frisoerprojektbackend.model;

import jakarta.persistence.*;

@Entity
public class BookingTreatments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingTreatmentsId")
    private int Id;


    private Booking bookingId;
    private int treatmentId;
}
