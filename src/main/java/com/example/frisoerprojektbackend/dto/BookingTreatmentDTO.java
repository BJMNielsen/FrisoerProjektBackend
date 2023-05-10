package com.example.frisoerprojektbackend.dto;

import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.model.Treatment;
import com.example.frisoerprojektbackend.model.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookingTreatmentDTO {

    private Booking booking;

    private List<Treatment> listOfTreatments;

    private UserProfile userProfile;


    public void checkForMistakes() {
        if (booking == null) {
            throw new ResourceNotFoundException("It was forgotten to send over a booking with the BookingTreatmentDTO");
        }
        if (booking.getTimeSlot() == null) {
            throw new ResourceNotFoundException("It was forgotten to send over a TimeSlot within the Booking with the BookingTreatmentDTO");
        }
        if (listOfTreatments == null) {
            throw new ResourceNotFoundException("It was forgotten to send over a list of Treatments with the BookingTreatmentDTO");
        }
        if (userProfile == null) {
            throw new ResourceNotFoundException("It was forgotten to send over a UserProfile with the BookingTreatmentDTO");
        }
    }

}

