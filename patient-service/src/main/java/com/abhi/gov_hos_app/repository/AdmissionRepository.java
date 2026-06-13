package com.abhi.gov_hos_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.gov_hos_app.entity.Admission;

public interface AdmissionRepository extends JpaRepository<Admission, Long> {

}
