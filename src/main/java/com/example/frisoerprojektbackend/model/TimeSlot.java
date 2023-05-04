package com.example.frisoerprojektbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slot_id")
    private int id;
    private LocalTime startTime;
    private LocalTime endTime;

    // Vi mapper timeSlot variablen inde i booking til vores timeSlotBookings
    @OneToMany(mappedBy = "timeSlot") // one timeslot to many bookings.
    @JsonBackReference
    private List<Booking> timeSlotBookings;
}
