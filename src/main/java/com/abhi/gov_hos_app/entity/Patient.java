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
	@JsonManagedReference("patient-admissions")
	private List<Admission> admissions;

	public Patient(Long patientId, String firstName, String lastName,
				   String phoneNo, Date DoB, String gender, City city,
				   String email, int status) {
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.DoB = DoB;
		this.gender = gender;
		this.city = city;
		this.email = email;
		this.status = status;
	}
}