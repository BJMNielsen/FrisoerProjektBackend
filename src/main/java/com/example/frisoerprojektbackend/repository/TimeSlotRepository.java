package com.example.frisoerprojektbackend.repository;

import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.model.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotRepository extends JpaRepository<TimeSlot,Integer> {


    // When you use the @Query annotation, you need to provide the query string as a parameter.
    // You can use the query string to specify the SQL or JPQL statement that should be executed.
    // In this example, the named parameter :date is used in the query string.
    // The @Param annotation is used to map the date parameter to the named parameter in the query string.

    // Vi vælger alle timeslots hvor timeslot id'et ikke kan findes i en booking der er lavet på den givne dato, hvor dagen ikke er lørdag eller søndag.
    @Query("""
            SELECT t FROM TimeSlot t WHERE t.id NOT IN
            (SELECT b.timeSlot.id FROM Booking b WHERE b.date = :date) 
            AND (DAYOFWEEK(:date) != 1 AND DAYOFWEEK(:date) != 7)
    """)

    List<TimeSlot> findAvailableTimeSlotsByDate(@Param("date") LocalDate date);

}
