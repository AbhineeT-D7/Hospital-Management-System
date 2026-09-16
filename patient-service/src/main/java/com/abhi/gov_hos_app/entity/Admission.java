package com.abhi.gov_hos_app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "admission")
@ToString(exclude = {"patient", "staff"})
public class Admission {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AA_ADMISSION_SEQ")
	@SequenceGenerator(name = "AA_ADMISSION_SEQ", sequenceName = "AA_ADMISSION_SEQ", allocationSize = 1)
	private Long admissionId;

	private LocalDateTime createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_Id")
	@JsonBackReference("patient-admissions")
	private Patient patient;

	@Column(name = "staffId")
	private Long staffId;

	private int status;

	public Admission(int status, Long staffId, Patient patient, LocalDateTime createdDate, Long admissionId) {
		this.status = status;
		this.staffId = staffId;
		this.patient = patient;
		this.createdDate = createdDate;
		this.admissionId = admissionId;
	}
}