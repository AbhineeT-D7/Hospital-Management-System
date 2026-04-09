package com.abhi.gov_hos_app.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReceiptDto {
	private Long receiptId;
	
	private String detail;
	private String barcode;
	private String drug_detail;
	private String usage;
	private String delivery_date;
	private int status;
	private Long patientId;
	private Long problemId;
}
