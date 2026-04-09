package com.abhi.gov_hos_app.controller;

import com.abhi.gov_hos_app.dto.StaffDto;
import com.abhi.gov_hos_app.entity.Staff;
import com.abhi.gov_hos_app.entity.enums.City;
import com.abhi.gov_hos_app.entity.enums.Department;
import com.abhi.gov_hos_app.exception.NotFoundException;
import com.abhi.gov_hos_app.service.StaffService;
import com.abhi.gov_hos_app.util.ApiPaths;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController("/staff")
@CrossOrigin(origins = "*")
//@RequestMapping("/staff")
@RequestMapping(ApiPaths.staffCtrl.CTRL)
public class StaffController {
	private final StaffService staffService;
	
	
	public StaffController(StaffService staffService) {
		this.staffService = staffService;
	}
	// headers = "Accept=application/json",
	//@RequestMapping(method = RequestMethod.GET, produces = "application/json")

	@GetMapping("/test")
	public String test(){
		return "Controller is working";
	}

	@GetMapping
	public ResponseEntity<List<StaffDto>> getAllStaff() throws NotFoundException {
		return ResponseEntity.ok(staffService.getAll());
	}
	
	@RequestMapping(value = "/deleted-staff",method = RequestMethod.GET, produces = "application/json")
	//@GetMapping("/deleted-staff")
	public ResponseEntity<List<StaffDto>> getAllDeletedStaff() throws NotFoundException {
		return ResponseEntity.ok(staffService.getAllDeletedStaff());
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<StaffDto> savePatient(@Valid @RequestBody Staff staff) throws Exception {
		return ResponseEntity.ok(staffService.save(staff));
	}
	
	@DeleteMapping("/{staffId}")
	public ResponseEntity<Boolean> deletePatient(@PathVariable(name = "staffId", required = true) Long staffId)
			throws Exception {
		return ResponseEntity.ok(staffService.delete(staffId));
	}
	
	@GetMapping("/cities")
	public ResponseEntity<List<City>> getAllCities() {
		return ResponseEntity.ok(Arrays.asList(City.values()));
	}
	
	@GetMapping("/department")
	public ResponseEntity<List<Department>> getAllDepartment() {
		return ResponseEntity.ok(Arrays.asList(Department.values()));
	}
}
