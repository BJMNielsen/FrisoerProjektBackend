package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.exception.ResourceAlreadyExistsException;
import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.model.TimeSlot;
import com.example.frisoerprojektbackend.repository.BookingRepository;
import com.example.frisoerprojektbackend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }


    public List<Booking> getBookingsByUserProfileId(int id) {
        boolean noUserExists = !userProfileRepository.existsById(id);

        if (noUserExists) {
            throw new ResourceNotFoundException("There is no user with the id of: " + id + " so it was not possible to find any bookings for that user");
        }
        return bookingRepository.findBookingsByBookingUserProfileId(id);
    }

    public List<Booking> getBookingsByDate(LocalDate date) {
        return bookingRepository.findBookingByDate(date);
    }


    public ResponseEntity<Booking> addBooking(Booking booking) {
        boolean exists = bookingRepository.existsById(booking.getId());
        if (exists) {
            // Tjekker om der allerede eksistere en userprofile med et ID. Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Booking with id: " + booking.getId() + " already exists and therefore can't be added.");
        } else {
            // Skaffer alle bookings der er sker på den dato, som den posted bookning er sat til
            LocalDate postBookningDate = booking.getDate();
            TimeSlot postBookningTimeSlot = booking.getTimeSlot();
            List<Booking> bookningsOnThatDate = getBookingsByDate(postBookningDate);
            // Går igennem listen af bookings for den givne dato
            for (Booking bookingOnThatDate: bookningsOnThatDate) {
                TimeSlot timeSlotForBooking = bookingOnThatDate.getTimeSlot();
                // Hvis den bookning der ønskes at blive tilføjet til databasen, har den samme TimeSlot som en af de andre bookninger for den dag, så vil man få en fejl tilbage
                if (postBookningTimeSlot.getId() == timeSlotForBooking.getId()) {
                    throw new ResourceAlreadyExistsException("There already exists a Booking with the TimeSlot: " + postBookningTimeSlot.getStartTime() + " - " + postBookningTimeSlot.getEndTime() + " on the date of: " + postBookningDate + ", so it is not possible to add the Booking to the database");
                }
            }
            // Hvis den ikke existere, og email ikke eksistere, så laver vi en ny userprofile og gemmer i databasen.
            Booking newBooking = bookingRepository.save(booking);
            return new ResponseEntity<>(newBooking, HttpStatus.OK);
        }
    }


}
