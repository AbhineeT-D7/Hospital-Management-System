package com.abhi.gov_hos_app.exception;

import java.util.Date;

import com.abhi.gov_hos_app.entity.Patient;
import com.abhi.gov_hos_app.entity.Problem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExceptionResponse {
	private Date date;
	private String message;
}
