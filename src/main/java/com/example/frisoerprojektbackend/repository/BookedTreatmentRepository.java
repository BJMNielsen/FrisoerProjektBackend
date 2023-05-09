package com.example.frisoerprojektbackend.repository;

import com.example.frisoerprojektbackend.model.BookedTreatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedTreatmentRepository extends JpaRepository<BookedTreatment, Integer> {
}
