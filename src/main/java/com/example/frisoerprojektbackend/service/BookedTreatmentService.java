package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.BookedTreatment;
import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.model.Treatment;
import com.example.frisoerprojektbackend.repository.BookedTreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedTreatmentService {

    @Autowired
    BookedTreatmentRepository bookedTreatmentRepository;

    public List<BookedTreatment> getBookedTreatment() {
        return bookedTreatmentRepository.findAll();
    }

    public BookedTreatment getBookedTreatmentById(int id) {
        return bookedTreatmentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Could not find a BookedTreatment with the ID: " + id + " from the database"));
    }

    public ResponseEntity<BookedTreatment> addBookedTreatment(Booking booking, int id) {

    }
}
