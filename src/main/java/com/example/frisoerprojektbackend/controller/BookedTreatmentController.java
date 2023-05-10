package com.example.frisoerprojektbackend.controller;

import com.example.frisoerprojektbackend.model.BookedTreatment;
import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.service.BookedTreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class BookedTreatmentController {

    @Autowired
    BookedTreatmentService bookedTreatmentService;

    @GetMapping("/bookedtreatments")
    public List<BookedTreatment> getBookedTreatments() {
        return bookedTreatmentService.getBookedTreatment();
    }

    @GetMapping("/bookedtreatment/{id}")
    public BookedTreatment getBookedTreatmentById(@PathVariable int id) {
        return bookedTreatmentService.getBookedTreatmentById(id);
    }
}
