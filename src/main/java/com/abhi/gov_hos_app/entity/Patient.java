package com.abhi.gov_hos_app.entity;

import com.abhi.gov_hos_app.entity.enums.City;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AA_PATIENT_SEQ")
	@SequenceGenerator(name = "AA_PATIENT_SEQ", sequenceName = "AA_PATIENT_SEQ", allocationSize = 1)
	@Column(name = "patient_id")
	private Long patientId;

	private String name;
	private String lastname;
	private String phoneNo;
	private Date bornDate;
	private String gender;

	@Enumerated(EnumType.ORDINAL)
	private City city;

	@Column(unique = true)
	private String email;

	private int status;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Problem> problems;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Admission> admissions;


	public Patient(String fName, String lName, String gender, City city, String mail, int id) {
		this.name = fName;
		this.lastname = lName;
		this.gender = gender;
		this.city =  city;
		this.email = mail;
		this.status = id;
	}
}