package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.exception.ResourceAlreadyExistsException;
import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.TimeSlot;
import com.example.frisoerprojektbackend.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TimeSlotService {

    @Autowired
    TimeSlotRepository timeSlotRepository;

    public List<TimeSlot> getTimeSlots() {
        return timeSlotRepository.findAll();
    }

    public ResponseEntity<TimeSlot> addTimeSlot(TimeSlot timeSlot) {
        boolean exists = timeSlotRepository.existsById(timeSlot.getId());
        if (exists) {
            // Tjekker om der allerede eksistere en timeslot med et ID. Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Timeslot with id: " + timeSlot.getId() + " already exists and therefore can't be added.");
        } else {
            // Hvis den ikke existere, og email ikke eksistere, så laver vi en ny userprofile og gemmer i databasen.
            TimeSlot newTimeSlot = timeSlotRepository.save(timeSlot);
            return new ResponseEntity<>(newTimeSlot, HttpStatus.OK);
        }
    }

    public TimeSlot getTimeSlotById(int id) {
        return timeSlotRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Timeslot with id: " + id + " does not exist and could therefore not find any timeslot."));
    }

    public List<TimeSlot> findAvailableTimeSlotsByDate(LocalDate date) {
        return timeSlotRepository.findAvailableTimeSlotsByDate(date);
    }
}
