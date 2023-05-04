package com.example.frisoerprojektbackend.model;

import jakarta.persistence.*;

@Entity
public class BookingTreatments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_treatments_id")
    private int id;

    @ManyToOne // Many BookingTreatments to one Booking
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id") // Her kalder vi vores foreign key column "booking_id", og værdierne den lægger ind sætter vi til at være dem der findes i referenceColumnName = "booking_id" variablen inde fra Booking klassen.
    private Booking booking;

    @ManyToOne // Many BookingTreatments to one Treatment
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id") // Her kalder vi vores foreign key column "treatment_id", og værdierne den lægger ind sætter vi til at være dem der findes i referenceColumnName = "treatment_id" variablen inde fra Treatment klassen.
    private Treatment treatment;
}
