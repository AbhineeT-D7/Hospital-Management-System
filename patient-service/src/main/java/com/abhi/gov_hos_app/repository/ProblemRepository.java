package com.abhi.gov_hos_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.gov_hos_app.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

	List<Problem> findByPatientPatientIdAndStatusOrderByProblemIdAsc(Long patientId, int status);

	List<Problem> findByStatusOrderByProblemIdAsc(int status);
}