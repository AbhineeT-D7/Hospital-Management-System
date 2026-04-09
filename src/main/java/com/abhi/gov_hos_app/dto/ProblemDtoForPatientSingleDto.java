package com.abhi.gov_hos_app.dto;

import java.io.Serializable;
import java.util.Date;

import com.abhi.gov_hos_app.entity.Patient;
import com.abhi.gov_hos_app.entity.Problem;
import com.abhi.gov_hos_app.entity.enums.ProblemStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProblemDtoForPatientSingleDto  implements Serializable {
	private Long problemId;
	private String problemName;
	private String problemDetail;
	private ProblemStatus problemStatus;
	private Long pId;
	private int status;
	private Date creationDate;

}
