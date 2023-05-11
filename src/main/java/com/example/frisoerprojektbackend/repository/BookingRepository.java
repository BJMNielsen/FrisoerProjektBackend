package com.example.frisoerprojektbackend.repository;

import com.example.frisoerprojektbackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {

  List<Booking> findBookingsByBookingUserProfileId(int id);

  List<Booking> findBookingByDate(LocalDate date);

  @Query("""
          SELECT b FROM Booking b WHERE b.date < CURRENT_DATE OR
          (b.date = CURRENT_DATE AND b.timeSlot.endTime < CURRENT_TIME)
          """)

  List<Booking> findPastAndCurrentDateBookings();

  @Query("""
          SELECT b FROM Booking b WHERE b.date >= CURRENT_DATE OR
          (b.date = CURRENT_DATE AND b.timeSlot.endTime > CURRENT_TIME)
          """)
  List<Booking> findFutureAndCurrentDateBookings();
}
