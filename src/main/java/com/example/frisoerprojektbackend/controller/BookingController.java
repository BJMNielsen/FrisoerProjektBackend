package com.example.frisoerprojektbackend.controller;

import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin
@RestController
public class BookingController {

  @Autowired
  BookingService bookingService;

  @GetMapping("/bookings")
  public List<Booking> getBookings() {
    return bookingService.getBookings();
  }

  @GetMapping("/bookings/userid/{id}")
  public List<Booking> getBookingsByUserProfileId(@PathVariable int id) {
    return bookingService.getBookingsByUserProfileId(id);
  }

  @PostMapping("/booking")
  public ResponseEntity<Booking> addBooking(@RequestBody Booking booking) {
    return bookingService.addBooking(booking);
  }

}