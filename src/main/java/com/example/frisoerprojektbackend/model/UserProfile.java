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
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private int id;

    private String name;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    private String password;

    // One UserProfile to many Bookings.
    // "bookingUserProfile" refers to the field bookingUserProfile in the Booking class that maps the relationship back to the UserProfile entity.
    @OneToMany(mappedBy = "bookingUserProfile", cascade = CascadeType.REMOVE) // Vi mapper vores liste af bookings til bookingUserProfile variablen i Booking klassen.
    @JsonBackReference
    private List<Booking> userProfileListOfBookings;

    // The mappedBy attribute in the @OneToMany annotation is set to "bookingUserProfile",
    // which corresponds to the bookingUserProfile field in the Booking entity.
    // This establishes the bidirectional relationship between UserProfile and Booking,
    // where the Booking entity is the inverse side and the UserProfile entity is the owning side.
}
