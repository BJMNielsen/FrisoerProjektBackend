package com.example.frisoerprojektbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "booked_treatments")
public class BookedTreatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_treatments_id")
    private int id;

    @ManyToOne // Many BookedTreatment to one Booking
    @JoinColumn(name = "booking_id", referencedColumnName = "booking_id") // Her kalder vi vores foreign key column "booking_id", og værdierne den lægger ind sætter vi til at være dem der findes i referenceColumnName = "booking_id" variablen inde fra Booking klassen.
    private Booking booking;

    @ManyToOne // Many BookedTreatment to one Treatment
    @JoinColumn(name = "treatment_id", referencedColumnName = "treatment_id") // Her kalder vi vores foreign key column "treatment_id", og værdierne den lægger ind sætter vi til at være dem der findes i referenceColumnName = "treatment_id" variablen inde fra Treatment klassen.
    private Treatment treatment;

    // Price ligger her, og ikke i bookings fordi vi skal vise prisen for den treatment vi bestiller på DAVÆRENDE tidspunkt.
    // Hvis vi ligger price inde under booking, så vil alle tidligere bookings få ændret pris, hvis vi en dag ændre prisen for en treatment.
    // treatments viser nuværende pris, som den står i tabellen treatments og booked_treatment viser den aktuelle pris da bookningen blev lavet.
    private double price;
}
