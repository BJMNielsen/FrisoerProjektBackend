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
@Table(name = "treatments")
public class Treatment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "treatment_id")
    private int id;

    private double price;

    @Column(unique = true)
    private String name;

    // One Treatment to many BookedTreatment.
    @OneToMany(mappedBy = "treatment") // Her referere vi til treatment variablen inde i BookedTreatment klassen.
    @JsonBackReference
    private List<BookedTreatment> listOfBookingTreatments;

}
