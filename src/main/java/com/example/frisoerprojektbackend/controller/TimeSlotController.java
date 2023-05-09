package com.example.frisoerprojektbackend.controller;

import com.example.frisoerprojektbackend.model.TimeSlot;
import com.example.frisoerprojektbackend.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TimeSlotController {

    @Autowired
    TimeSlotService timeSlotService;

    @GetMapping("/timeslots")
    public List<TimeSlot> getTimeSlots() {
        return timeSlotService.getTimeSlots();
    }

    @GetMapping("/timeslot/{id}")
    public TimeSlot getTimeSlotById(@PathVariable int id) {
        return timeSlotService.getTimeSlotById(id);
    }

    @PostMapping("/timeslot")
    public ResponseEntity<TimeSlot> addTimeSlot(@RequestBody TimeSlot timeSlot) {
        return timeSlotService.addTimeSlot(timeSlot);
    }



}
