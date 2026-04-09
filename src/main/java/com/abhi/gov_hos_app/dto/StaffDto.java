package com.abhi.gov_hos_app.dto;

import com.abhi.gov_hos_app.entity.enums.City;
import com.abhi.gov_hos_app.entity.enums.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StaffDto implements Serializable {
	
	private Long staffId;
	private String staffName;
	private String staffLastName;
	private String gender;
	private String email;
	private City city;
	private Department department;
    private Date DateOfBirth;
}
