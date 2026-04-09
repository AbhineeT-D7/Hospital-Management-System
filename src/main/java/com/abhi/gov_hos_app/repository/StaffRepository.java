package com.abhi.gov_hos_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.gov_hos_app.entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {

	List<Staff> findByStatusOrderByStaffIdAsc(int status);

}