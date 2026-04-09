package com.abhi.gov_hos_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
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
	private Patient patient;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staffId")
	private Staff staff;

	private int status;
}