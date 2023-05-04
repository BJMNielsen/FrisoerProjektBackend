package com.example.frisoerprojektbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_slot_id")
    private int id;

    private LocalTime startTime;

    private LocalTime endTime;

    // Vi mapper timeSlot variablen inde i booking til vores timeSlotBookings
    @OneToMany(mappedBy = "timeSlot") // One TimeSlot to many Bookings.
    @JsonBackReference
    private List<Booking> timeSlotBookings;
}
