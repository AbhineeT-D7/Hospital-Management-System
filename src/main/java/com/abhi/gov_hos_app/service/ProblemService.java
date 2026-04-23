package com.abhi.gov_hos_app.service;

import com.abhi.gov_hos_app.dto.*;
import com.abhi.gov_hos_app.entity.Patient;
import com.abhi.gov_hos_app.entity.Problem;
import com.abhi.gov_hos_app.exception.NotFoundException;
import com.abhi.gov_hos_app.repository.PatientRepository;
import com.abhi.gov_hos_app.repository.ProblemRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

	private final ProblemRepository problemRepository;
	private final PatientRepository patientRepository;
	private final ModelMapper modelMapper;
	private final Logger logger;

	public ProblemService(ProblemRepository problemRepository,
						  PatientRepository patientRepository,
						  ModelMapper modelMapper,
						  Logger logger) {
		this.problemRepository = problemRepository;
		this.patientRepository = patientRepository;
		this.modelMapper = modelMapper;
		this.logger = logger;
	}

	public ProblemDtoForPatientSingleDto save(ProblemDto dto) {

		Patient patient = patientRepository.findById(dto.getPId())
				.orElseThrow(() ->
						new NotFoundException("Patient not found with id " + dto.getPId()));

		Problem problem = modelMapper.map(dto, Problem.class);
		problem.setPatient(patient);
		problem.setStatus(1);

		problem = problemRepository.save(problem);

		return modelMapper.map(problem, ProblemDtoForPatientSingleDto.class);
	}

	public Boolean delete(Long problemId) {
		Problem problem = problemRepository.findById(problemId)
				.orElseThrow(() ->
						new NotFoundException("Problem not found " + problemId));

		problem.setStatus(0);
		problemRepository.save(problem);
		return true;
	}

	public List<ProblemDtoForPatientSingleDto> findAllByPatientId(Long patientId) {

		List<Problem> list = problemRepository.findByPatientPatientIdAndStatusOrderByProblemIdAsc(
				patientId, 1);

		if (list.isEmpty())
			throw new NotFoundException("No problems found");

		return Arrays.asList(
				modelMapper.map(list, ProblemDtoForPatientSingleDto[].class)
		);
	}

	public ProblemGetDto findByProblemId(Long problemId) {

		Problem problem = problemRepository.findById(problemId)
				.orElseThrow(() ->
						new NotFoundException("Problem not found with id " + problemId));

		return modelMapper.map(problem, ProblemGetDto.class);
	}

	public Boolean update(Long problemId, @Valid ProblemDto dto) {

		Problem existing = problemRepository.findById(problemId)
				.orElseThrow(() ->
						new NotFoundException("Problem not found with id " + problemId));

		existing.setProblemName(dto.getProblemName());
		existing.setProblemDetail(dto.getProblemDetail());
		existing.setProblemStatus(dto.getProblemStatus());

		if (dto.getPId() != null) {
			Patient patient = patientRepository.findById(dto.getPId())
					.orElseThrow(() ->
							new NotFoundException("Patient not found with id " + dto.getPId()));
			existing.setPatient(patient);
		}

		problemRepository.save(existing);

		return true;
	}

}