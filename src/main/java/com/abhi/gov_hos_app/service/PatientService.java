package com.abhi.gov_hos_app.service;

import com.abhi.gov_hos_app.dto.PatientDto;
import com.abhi.gov_hos_app.dto.PatientSingleDto;
import com.abhi.gov_hos_app.entity.Patient;
import com.abhi.gov_hos_app.exception.PatientNotFoundException;
import com.abhi.gov_hos_app.repository.PatientRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private final PatientRepository patientRepository;
	private final ModelMapper modelMapper;
	private final Logger logger;

	public PatientService(PatientRepository patientRepository, ModelMapper modelMapper, Logger logger) {
		this.patientRepository = patientRepository;
		this.modelMapper = modelMapper;
		this.logger = logger;
	}

	public List<PatientDto> findAll() throws Exception {
		try { 
			// List<Patient> patients = patientRepository.findAllByOrderByPatientIdAsc();
			List<Patient> patients = patientRepository.findByStatusOrderByPatientIdAsc(1);
			if (patients.isEmpty()) {
				logger.error("There is never patients ");
				throw new PatientNotFoundException("There is never patient ");
			}
			PatientDto[] dtos = modelMapper.map(patients, PatientDto[].class);
			List<PatientDto> patientDtos = Arrays.asList(dtos);
			patientDtos.forEach(patient->{
				patient.getProblems().forEach(problem->{
					problem.setPId(patient.getPatientId());
				});
			});
			return Arrays.asList(dtos);
		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	public List<PatientDto> findAllDeletedPatients() { 
		List<Patient> patients = patientRepository.findByStatus(0);
		if (!patients.isEmpty()) {
			PatientDto[] authorDtos = modelMapper.map(patients, PatientDto[].class);
			return Arrays.asList(authorDtos);
		} else {
			logger.error("There is no deleted patient ");
			throw new PatientNotFoundException("There is no deleted patient "); 
		}
	}

	public Patient save(Patient patient) {
		patient.setStatus(1);
		patient = patientRepository.save(patient);
		if (patient.getPatientId() > -1)
			return patient;
		else{
			logger.error("A problem occurred during saving patient" );
			throw new PatientNotFoundException("A problem occurred during saving patient" );
		}
	}

	public Boolean delete(@Valid Long patientId) throws Exception {
		Optional<Patient> optPatient = patientRepository.findById(patientId);
		if (optPatient.isPresent()) { 
			optPatient.get().setStatus(0);
			optPatient.get().getProblems().forEach(p -> {
				p.setStatus(0);
			});
			patientRepository.save(optPatient.get());
			// patientRepository.delete(optpatient.get());
			return true;
		} else {
			logger.error("--Patient does not exist with this id " + patientId);
			throw new PatientNotFoundException("Patient does not exist with this id " + patientId);
		}
	}

	public PatientSingleDto findByPatientId(Long patientId) throws Exception {
		Optional<Patient> optPatient = patientRepository.findById(patientId);
		if (optPatient.isPresent()) { 
			optPatient.get().getProblems().removeIf(problem -> problem.getStatus() == 0);
			PatientSingleDto dto = modelMapper.map(optPatient.get(), PatientSingleDto.class); 
			return dto;
		} else { 
			logger.error("--Patient does not exist with this id " + patientId);
			throw new PatientNotFoundException("Patient does not exist with this id " + patientId);
		}
	}

	public Patient findByEmail(String email) throws Exception {
		Optional<Patient> patient = patientRepository.findByEmail(email);
		if (patient.isPresent()) 
			return patient.get();
		else { 
			logger.error("--Patient does not exist with this email " + email);
			throw new PatientNotFoundException("Patient does not exist with this email " + email);
		}
	}

	public Boolean update(Long patientId, @Valid Patient patient) throws Exception {
		Optional<Patient> p = patientRepository.findById(patientId);
		if (p.isPresent()) { 
			patient.setPatientId(patientId);
			patientRepository.save(patient); 
			return true;
		} else {
			logger.error("--Patient does not exist with this id " + patientId);
			throw new PatientNotFoundException("Patient does not exist with this id " + patientId);
		}
	}

	public List<Patient> findByName(String name) throws Exception { 
		List<Patient> patients = patientRepository.findAllByOrderByNameAsc();
		if (!patients.isEmpty()) {
			return patients;
		} else { 
			logger.error("--Patient does not exist with this name " + name);
			throw new PatientNotFoundException("Patient does not exist with this name " + name);
		}
	}

}
