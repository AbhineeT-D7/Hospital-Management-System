package com.abhi.gov_hos_app.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.abhi.gov_hos_app.entity.enums.City;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PatientSingleDto implements Serializable {
	private Long patientId;
	private String name;
	private String lastName;
	private String phoneNo;
	private Date DateOfBirth;
	private String gender;
	private City city;
	private String email;
	private int status;
	
	private List<ProblemDtoForPatientSingleDto> problems;
}
