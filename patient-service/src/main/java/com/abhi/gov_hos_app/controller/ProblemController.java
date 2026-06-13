package com.abhi.gov_hos_app.controller;

import com.abhi.gov_hos_app.dto.ProblemDto;
import com.abhi.gov_hos_app.dto.ProblemDtoForPatientSingleDto;
import com.abhi.gov_hos_app.dto.ProblemGetDto;
import com.abhi.gov_hos_app.entity.enums.ProblemStatus;
import com.abhi.gov_hos_app.exception.NotFoundException;
import com.abhi.gov_hos_app.service.ProblemService;
import com.abhi.gov_hos_app.util.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController("/problem")
@CrossOrigin(origins = "*")
@RequestMapping(ApiPaths.ProblemCtrl.CTRL)
public class ProblemController {

	@Autowired
	ProblemService problemService;

	@GetMapping("/find-by-problemId/{problemId}")
	public ResponseEntity<ProblemGetDto> getProblem(@PathVariable(name = "problemId", required = true) Long problemId)
			throws NotFoundException {
		return ResponseEntity.ok(problemService.findByProblemId(problemId));
	}

	@GetMapping("/find-all-by-patientId/{patientId}")
	public ResponseEntity<List<ProblemDtoForPatientSingleDto>> getAllProblem(@PathVariable(name = "patientId", required = true) Long patientid)
			throws NotFoundException {
		return ResponseEntity.ok(problemService.findAllByPatientId(patientid));
	}
	@PostMapping
	public ResponseEntity<ProblemDtoForPatientSingleDto> saveProblem(@Valid @RequestBody ProblemDto dto)
			throws NotFoundException {
		return ResponseEntity.ok(problemService.save(dto));
	}

	@PutMapping("/{problemId}")
	public ResponseEntity<Boolean> update(@PathVariable Long problemId,
										  @RequestBody ProblemDto dto) {
		return ResponseEntity.ok(problemService.update(problemId, dto));
	}

	@DeleteMapping("/{problemId}")
	public ResponseEntity<Boolean> deleteProblem(@PathVariable(name = "problemId", required = true) Long problemId)
			throws Exception {
		return ResponseEntity.ok(problemService.delete(problemId));
	}

	@GetMapping("/status")
	public ResponseEntity<List<ProblemStatus>> getAllBookStatus() {
		return ResponseEntity.ok(Arrays.asList(ProblemStatus.values()));
	}
}
