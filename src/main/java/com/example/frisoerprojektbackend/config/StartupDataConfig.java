package com.example.frisoerprojektbackend.config;


import com.example.frisoerprojektbackend.model.*;
import com.example.frisoerprojektbackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


//@Configuration annotationen og ApplicationRunner klassen gør at run metoden køre når applikationen starter.
@Configuration
public class StartupDataConfig implements ApplicationRunner {

    // Indsæt autowired her for ønsket funktionalitet.
    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    TreatmentRepository treatmentRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    BookedTreatmentRepository bookedTreatmentRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Starter program op");

        createTimeSlots();
        createTreatments();
        List<UserProfile> userProfiles = createUserProfiles();
        createBookingsForUsers(userProfiles);



    }

    private void createBookingsForUsers(List<UserProfile> userProfiles) {
        List<TimeSlot> allTimeSlots = timeSlotRepository.findAll();
        userProfiles.forEach(userProfile -> {
            Booking booking = new Booking();
            booking.setBookingUserProfile(userProfile);
            booking.setTimeSlot(allTimeSlots.get(0));
            booking.setDate(LocalDate.now().plusDays(userProfile.getId()));
            Booking createdBooking = bookingRepository.save(booking);
            createBookedTreatment(createdBooking);

            if (userProfile.getName().equals("Lars Larsen")) {
                Booking larsBooking = new Booking();
                larsBooking.setBookingUserProfile(userProfile);
                larsBooking.setTimeSlot(allTimeSlots.get(1));

                larsBooking.setDate(LocalDate.now().plusDays(userProfile.getId()));
                Booking createdLarsBooking = bookingRepository.save(larsBooking);
                createBookedTreatment(createdLarsBooking);
            }
        });
    }

    private void createBookedTreatment(Booking booking) {
        List<Treatment> allTreatments = treatmentRepository.findAll();

        boolean unEqualNumber = booking.getBookingUserProfile().getId() % 2 != 0;
        Treatment haarvask = allTreatments.get(2);
        if (unEqualNumber) {
            Treatment mandeklip = allTreatments.get(0);
            BookedTreatment bookedMandeklip = new BookedTreatment();
            bookedMandeklip.setBooking(booking);
            bookedMandeklip.setTreatment(mandeklip);
            bookedMandeklip.setPrice(mandeklip.getPrice());
            bookedTreatmentRepository.save(bookedMandeklip);

            BookedTreatment bookedHaarvask1 = new BookedTreatment();
            bookedHaarvask1.setBooking(booking);
            bookedHaarvask1.setTreatment(haarvask);
            bookedHaarvask1.setPrice(haarvask.getPrice());
            bookedTreatmentRepository.save(bookedHaarvask1);

        } else {
            Treatment kvindeklip = allTreatments.get(1);
            BookedTreatment bookedKvindeklip = new BookedTreatment();
            bookedKvindeklip.setBooking(booking);
            bookedKvindeklip.setTreatment(kvindeklip);
            bookedKvindeklip.setPrice(kvindeklip.getPrice());
            bookedTreatmentRepository.save(bookedKvindeklip);

            BookedTreatment bookedHaarvask2 = new BookedTreatment();
            bookedHaarvask2.setBooking(booking);
            bookedHaarvask2.setTreatment(haarvask);
            bookedHaarvask2.setPrice(haarvask.getPrice());
            bookedTreatmentRepository.save(bookedHaarvask2);
        }
    }

    private void createTreatments() {
        Treatment t1 = new Treatment(300, "Mandeklip");
        Treatment t2 = new Treatment(400, "Kvindeklip");
        Treatment t3 = new Treatment(100, "Hårvask");

        treatmentRepository.save(t1);
        treatmentRepository.save(t2);
        treatmentRepository.save(t3);
    }

    private List<UserProfile> createUserProfiles() {
        List<UserProfile> createdUserProfiles = new ArrayList<>();
        UserProfile user1 = new UserProfile();
        user1.setEmail("Lars@gmail.com");
        user1.setName("Lars Larsen");
        user1.setPhoneNumber("12345678");
        user1.setPassword("123");
        UserProfile createdUser1 = userProfileRepository.save(user1);
        createdUserProfiles.add(createdUser1);

        UserProfile user2 = new UserProfile();
        user2.setEmail("Trine@Gmail.com");
        user2.setName("Trine");
        user2.setPhoneNumber("87654321");
        user2.setPassword("321");
        UserProfile createdUser2 = userProfileRepository.save(user2);
        createdUserProfiles.add(createdUser2);

        return createdUserProfiles;
    }

    private void createTimeSlots() {
        TimeSlot t1 = new TimeSlot(LocalTime.of(9, 00), LocalTime.of(9,45));
        TimeSlot t2 = new TimeSlot(LocalTime.of(9, 45), LocalTime.of(10,30));
        TimeSlot t3 = new TimeSlot(LocalTime.of(10, 30), LocalTime.of(11,15));
        TimeSlot t4 = new TimeSlot(LocalTime.of(11, 15), LocalTime.of(12,00));
        // frokostpause
        TimeSlot t5 = new TimeSlot(LocalTime.of(12, 45), LocalTime.of(13,30));
        TimeSlot t6 = new TimeSlot(LocalTime.of(13, 30), LocalTime.of(14,15));
        TimeSlot t7 = new TimeSlot(LocalTime.of(14, 15), LocalTime.of(15,00));
        TimeSlot t8 = new TimeSlot(LocalTime.of(15, 00), LocalTime.of(15,45));
        TimeSlot t9 = new TimeSlot(LocalTime.of(15, 45), LocalTime.of(16,30));

        timeSlotRepository.save(t1);
        timeSlotRepository.save(t2);
        timeSlotRepository.save(t3);
        timeSlotRepository.save(t4);
        timeSlotRepository.save(t5);
        timeSlotRepository.save(t6);
        timeSlotRepository.save(t7);
        timeSlotRepository.save(t8);
        timeSlotRepository.save(t9);
    }
}
