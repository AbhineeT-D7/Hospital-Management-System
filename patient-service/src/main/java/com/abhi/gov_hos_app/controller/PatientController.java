package com.abhi.gov_hos_app.controller;

import com.abhi.gov_hos_app.dto.PatientDto;
import com.abhi.gov_hos_app.dto.PatientSingleDto;
import com.abhi.gov_hos_app.entity.Patient;
import com.abhi.gov_hos_app.entity.enums.City;
import com.abhi.gov_hos_app.service.PatientService;
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

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ApiPaths.PatientCtrl.CTRL)
//@RequestMapping("/api")
public class PatientController {


	@Autowired
	private PatientService patientService;

	@GetMapping("/test")
	public String test(){
		return "Controller is working";
	}

	@GetMapping
	public ResponseEntity<List<PatientDto>> getAll() throws Exception {
		return  ResponseEntity.ok(patientService.findAll());
	}

	@GetMapping("/find-by-id/{patientId}")
	public ResponseEntity<PatientSingleDto> getPatientByPatientId(
			@PathVariable(name = "patientId", required = true) Long patientId) throws Exception {
		return ResponseEntity.ok(patientService.findByPatientId(patientId));
	}

	@GetMapping("/find-by-email/{email}")
	public ResponseEntity<Patient> getPatientByEmail(@PathVariable(name = "email", required = true) String email)
			throws Exception {
		return ResponseEntity.ok(patientService.findByEmail(email));
	}

	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<List<Patient>> getPatientByFirstName(@PathVariable(name = "firstName", required = true) String firstName)
			throws Exception {
		return ResponseEntity.ok(patientService.findByFirstName(firstName));
	}

	@PostMapping
	public ResponseEntity<Patient> savePatient(@Valid @RequestBody Patient patient) {
		return ResponseEntity.ok(patientService.save(patient));
	}

	@PutMapping("/{patientId}")
	public ResponseEntity<Boolean> updatePatient(@PathVariable(name = "patientId", required = true) Long patientId,
			@Valid @RequestBody Patient patient) throws Exception {
		return ResponseEntity.ok(patientService.update(patientId, patient));
	}

	@DeleteMapping("/{patientId}")
	public ResponseEntity<Boolean> deletePatient(@PathVariable(name = "patientId", required = true) Long patientId)
			throws Exception {
		return ResponseEntity.ok(patientService.delete(patientId));
	}

	@GetMapping("/deleted-patient")
	public ResponseEntity<List<PatientDto>> getAllDeletedPatients() throws Exception {
		return ResponseEntity.ok(patientService.findAllDeletedPatients());
	}

	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCities() {
		return ResponseEntity.ok(Arrays.asList(City.values()));
	}
}
