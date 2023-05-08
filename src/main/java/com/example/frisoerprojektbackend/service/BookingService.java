package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.exception.ResourceAlreadyExistsException;
import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.model.UserProfile;
import com.example.frisoerprojektbackend.repository.BookingRepository;
import com.example.frisoerprojektbackend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
  @Autowired
  BookingRepository bookingRepository;

  public List<Booking> getBookings() {
    return bookingRepository.findAll();
  }

  public ResponseEntity<Booking> addBooking(Booking booking) {
    boolean exists = bookingRepository.existsById(booking.getId());
    if (exists) {
      // Tjekker om der allerede eksistere en userprofile med et ID. Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
      throw new ResourceAlreadyExistsException("Booking with id: " + booking.getId() + " already exists and therefore can't be added.");
    }

    else {
      // Hvis den ikke existere, og email ikke eksistere, så laver vi en ny userprofile og gemmer i databasen.
      Booking newBooking = bookingRepository.save(booking);
      return new ResponseEntity<>(newBooking, HttpStatus.OK);
    }
  }
}
