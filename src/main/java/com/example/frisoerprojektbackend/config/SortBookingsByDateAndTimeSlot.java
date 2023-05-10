package com.example.frisoerprojektbackend.config;

import com.example.frisoerprojektbackend.model.Booking;
import com.example.frisoerprojektbackend.model.TimeSlot;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Comparator;

@Component
public class SortBookingsByDateAndTimeSlot implements Comparator<Booking> {

    @Override
    public int compare(Booking booking1, Booking booking2) {
        LocalDate dateOfBooking1 = booking1.getDate();
        LocalDate dateOfBooking2 = booking2.getDate();

        if (dateOfBooking1.compareTo(dateOfBooking2) != 0) {
            return dateOfBooking1.compareTo(dateOfBooking2);
        }
        TimeSlot timeSlotOfBooking1 = booking1.getTimeSlot();
        TimeSlot timeSlotOfBooking2 = booking2.getTimeSlot();
        //timeSlotOfBooking1.getStartTime().compareTo(timeSlotOfBooking2.getStartTime());
        return timeSlotOfBooking1.getId() - timeSlotOfBooking2.getId();
    }
}
