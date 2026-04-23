package com.abhi.gov_hos_app.entity;

import com.abhi.gov_hos_app.entity.enums.ProblemStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name="problem")
@ToString(exclude = {"patient"})
public class Problem {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AA_PROBLEM_SEQ")
	@SequenceGenerator(name = "AA_PROBLEM_SEQ", sequenceName = "AA_PROBLEM_SEQ", allocationSize = 1)
	private Long problemId;

	private String problemName;
	private String problemDetail;

	@Enumerated(EnumType.STRING)
	private ProblemStatus problemStatus;

	private int status;

	private LocalDateTime createdDate;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@JsonBackReference("patient-problems")
	private Patient patient;

	@OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Receipt> receipts;

	public Problem(Long problemId, String problemName, String problemDetail, ProblemStatus problemStatus, int status, LocalDateTime createdDate, Patient patient) {
		this.problemId = problemId;
		this.problemName = problemName;
		this.problemDetail = problemDetail;
		this.problemStatus = problemStatus;
		this.status = status;
		this.createdDate = createdDate;
		this.patient = patient;
	}
}