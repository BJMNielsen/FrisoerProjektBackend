package com.example.frisoerprojektbackend.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeSlotId")
    private int Id;

    private LocalTime startTime;
    private LocalTime endTime;
}
