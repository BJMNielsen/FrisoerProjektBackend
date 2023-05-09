package com.example.frisoerprojektbackend.repository;

import com.example.frisoerprojektbackend.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    Optional<Treatment> findTreatmentByName(String name);

    boolean existsByName(String name);

}
