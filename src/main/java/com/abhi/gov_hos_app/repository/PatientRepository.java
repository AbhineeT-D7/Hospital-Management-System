package com.abhi.gov_hos_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.gov_hos_app.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

	List<Patient> findAllByOrderByPatientIdAsc();

	List<Patient> findAllByOrderByNameAsc();

	Optional<Patient> findByEmail(String email);

	List<Patient> findByNameContainingIgnoreCase(String name);

	List<Patient> findByStatusOrderByPatientIdAsc(int status);

	List<Patient> findByStatus(int status);
}