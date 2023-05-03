package com.example.frisoerprojektbackend.model;

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
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private int id;

    @Column(nullable = false) // Not null
    private String name;

    @Column(unique = true, nullable = false) // Not null og unique
    private String email;

    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false) // Not null
    private String password;

    // One UserProfile to many bookings.
    // "userProfile" refers to the field userProfile in the Booking class that maps the relationship back to the UserProfile entity.
    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE) // Vi mapper vores map af bookings til userProfile variablen i Booking klassen.
    @MapKey(name = "Id") // Bookings Id variabel.
    private List<Booking> userProfileBookings;

    // Her siger vi at vi gerne vil have en foreign key til teamet i form af en kolonne der hedder "teamid" (name = "teamId"),
    // hvis værdi skal være den samme som "id" i team klassen (referencedColumnName = "id").

    // The mappedBy attribute in the @OneToMany annotation is set to "userProfile",
    // which corresponds to the userProfile field in the Booking entity.
    // This establishes the bidirectional relationship between UserProfile and Booking,
    // where the Booking entity is the inverse side and the UserProfile entity is the owning side.

}
