package com.abhi.gov_hos_app.entity;

import com.abhi.gov_hos_app.entity.enums.City;
import com.abhi.gov_hos_app.entity.enums.Department;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "staff")
public class Staff {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AA_STAFF_SEQ")
	@SequenceGenerator(name = "AA_STAFF_SEQ", sequenceName = "AA_STAFF_SEQ", allocationSize = 1)
	private Long staffId;

	private String staffName;
	private String staffLastName;
	private String gender;
	private Date dateOfBirth;

	@Column(unique = true)
	private String email;

	@Enumerated(EnumType.ORDINAL)
	private City city;

	@Enumerated(EnumType.ORDINAL)
	private Department department;

	private int status;
}