package com.example.frisoerprojektbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Treatment {

    @Id
    @Column(name = "treatment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    @Column(unique = true)
    private String name;

    // One Treatment to many BookingTreatments.
    @OneToMany(mappedBy = "treatment") // Her referere vi til treatment variablen inde i BookingTreatments klassen.
    @JsonBackReference
    private List<BookingTreatments> listOfBookingTreatments;

}
