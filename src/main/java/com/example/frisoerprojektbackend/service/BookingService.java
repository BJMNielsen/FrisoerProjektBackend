package com.example.frisoerprojektbackend.service;

import com.example.frisoerprojektbackend.config.SortBookingsByDateAndTimeSlot;
import com.example.frisoerprojektbackend.dto.BookingTreatmentDTO;
import com.example.frisoerprojektbackend.exception.ResourceAlreadyExistsException;
import com.example.frisoerprojektbackend.exception.ResourceNotFoundException;
import com.example.frisoerprojektbackend.model.*;
import com.example.frisoerprojektbackend.repository.BookedTreatmentRepository;
import com.example.frisoerprojektbackend.repository.BookingRepository;
import com.example.frisoerprojektbackend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    BookedTreatmentRepository bookedTreatmentRepository;

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

        // Vi finder alle de bookings der har den samme dato som der blevet givet i parameteren: "date"
        List<Booking> foundBookingsByADate = bookingRepository.findBookingByDate(date);
        // Vi får den liste sorteret ud efter deres dato og derefter ud efter deres timeslots
        // Så alle de med en tidligere dato kommer først og dem som har samme dato
        // De vil blive sorteret så det er de bookings med en tidligere timeslot
        sortBookings(foundBookingsByADate);
        // Den nu sorteret liste bliver nu givet tilbage
        return foundBookingsByADate;
    }


    public ResponseEntity<Booking> addBooking(BookingTreatmentDTO bookingTreatmentDTO) {
        // i bookingTreatmentDTO har vi lavet en metode der lige tjekker om nogen af attributterne i DTO'en er null, og hvis de er throw en exception.
        bookingTreatmentDTO.checkForMistakes();
        // Vi giver metoden en DTO der indeholder en newBooking og en list af treatments. Dem gemmer vi i 2 variabler.
        Booking newBooking = bookingTreatmentDTO.getBooking();
        List<Treatment> treatmentList = bookingTreatmentDTO.getListOfTreatments();
        UserProfile userProfile = bookingTreatmentDTO.getUserProfile();

        // Vi tjekker først om UserProfile eksistere i databasen.
        boolean userProfileExist = userProfileRepository.existsById(userProfile.getId());
        if (!userProfileExist) {
            throw new ResourceNotFoundException("UserProfile is with id: " + userProfile.getId() + " does not exist, and therefore we cannot add a booking for the profile.");
        }
        // Så tjekker vi om newBooking allerede eksistere, hvis ja, så thrower vi en exception
        boolean exists = bookingRepository.existsById(newBooking.getId());
        if (exists) {
            // Tjekker om der allerede eksistere en userprofile med et ID. Hvis den existere thrower vi en exception der bliver grebet i vores exception controller og lavet til et responseEntity object, som vores frontend også kan håndtere.
            throw new ResourceAlreadyExistsException("Booking with id: " + newBooking.getId() + " already exists and therefore can't be added.");
        }
        // Ellers tjekker vi om det timeslot på den dato vi prøver at lave en bookning for, er optaget:

        // Skaffer dato på den newBooking vi vil lave.
        LocalDate newBookingDate = newBooking.getDate();
        // Skaffer den specifikke timeslot for bookingen der skal postes.
        TimeSlot newBookingTimeSlot = newBooking.getTimeSlot();

        // Skaffer alle bookings der er sker på den dato, som den posted bookning (newBooking) er sat til, gemmer i en liste.
        List<Booking> bookingsOnNewBookingsDate = getBookingsByDate(newBookingDate);

        // Går igennem listen af bookings for den givne dato
        for (Booking booking : bookingsOnNewBookingsDate) {
            // skaffer timeslot for hver booking og gemmer den i den her variabel
            TimeSlot timeSlotForBooking = booking.getTimeSlot();
            // Hvis den bookning der ønskes at blive tilføjet til databasen, har den samme TimeSlot som en af de andre bookninger for den dag, så vil man få en fejl tilbage
            if (newBookingTimeSlot.getId() == timeSlotForBooking.getId()) {
                throw new ResourceAlreadyExistsException("There already exists a Booking with the TimeSlot: " + newBookingTimeSlot.getStartTime() + " - " + newBookingTimeSlot.getEndTime() + " on the date of: " + newBookingDate + ", so it is not possible to add the Booking to the database");
            }
        }

        if (treatmentList.isEmpty()) {
            throw new ResourceNotFoundException("A Booking must have at least one treatment to be saved within the database");
        }
        // Vi setter vores newBookings userProfile attribut til at være den userProfile vi har sendt med over i BookingTreatmentDTO'en.
        newBooking.setBookingUserProfile(userProfile);

        // Hvis den ikke existere, og email ikke eksistere, så gemmer vi den nye booking i databasen, og store den i en variabel.
        Booking newBookingSaved = bookingRepository.save(newBooking);

        // Her bruger vi vores addTreatmentsToBooking metode til at gemme de treatments vi har sendt over i vores DTO, sammen med booking id'et i bookingTreatments tabellen.
        addBookingTreatmentsToBooking(treatmentList, newBookingSaved);
        return new ResponseEntity<>(newBookingSaved, HttpStatus.OK);
    }

    private void addBookingTreatmentsToBooking(List<Treatment> listOfTreatments, Booking booking) {
        List<BookedTreatment> listOfSavedBookedTreatments = new ArrayList<>();
        // for hver treatment i vores liste af treatments
        for (Treatment treatment : listOfTreatments) {
            // Laver vi en ny bookedTreatment, hvor vi setter bookedTreatments Treatment felt til at være den treatment vi er nået til i listen,
            // Samt sætter bookedTreatments booking felt til at være == booking parameteren vi overføre. Vi sætter også bookedTreatments pris.
            BookedTreatment bookedTreatment = new BookedTreatment();
            bookedTreatment.setTreatment(treatment);
            bookedTreatment.setBooking(booking);
            bookedTreatment.setPrice(treatment.getPrice());
            // så saver vi objektet. På den måde får vi saved alle treatments til én booking i flere rækker i vores separate bookingTreatments tabel.
            BookedTreatment bookedTreatmentSaved = bookedTreatmentRepository.save(bookedTreatment);
            // Vi adder vores saved bookedTreatment til vores liste af savedBookedTreatments
            listOfSavedBookedTreatments.add(bookedTreatmentSaved);
        }
        // Vi tager så vores booking, og sætter dens attribute felt kaldet bookingTreatmentList til at være vores liste af saved bookings.
        booking.setBookedTreatmentList(listOfSavedBookedTreatments);
    }

    // Sortere bookings efter dato og efter timeslots hvis bookingerne har samme dato.
    private void sortBookings(List<Booking> bookings) {
        SortBookingsByDateAndTimeSlot sortBookings = new SortBookingsByDateAndTimeSlot();
        bookings.sort(sortBookings);
    }
}



