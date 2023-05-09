package com.example.frisoerprojektbackend.controller;

import com.example.frisoerprojektbackend.model.Treatment;
import com.example.frisoerprojektbackend.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TreatmentController {

    @Autowired
    TreatmentService treatmentService;


    @GetMapping("/treatments")
    public List<Treatment> getTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/treatment/{id}")
    public Treatment getTreatmentById(@PathVariable int id) {
        return treatmentService.getTreatmentById(id);
    }

    // Der skal være "/name/" for at det ikke er metoden getTreatmentById der bliver kaldt
    @GetMapping("/treatment/name/{name}")
    public Treatment getTreatmentByName(@PathVariable String name) {
        return treatmentService.getTreatmentByName(name);
    }

    @PostMapping("/treatment")
    public ResponseEntity<Treatment> addTreatment(@RequestBody Treatment treatment) {
        return treatmentService.addTreatment(treatment);
    }

    @PutMapping("/treatment")
    public ResponseEntity<Treatment> updateTreatment(@RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(treatment);
    }
}