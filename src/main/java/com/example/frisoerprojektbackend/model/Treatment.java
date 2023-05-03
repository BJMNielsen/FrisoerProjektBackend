package com.example.frisoerprojektbackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Treatment {

    @Id
    @Column(name = "treatmentId")
    private int Id;
    private double Price;
    private String Name;
}
