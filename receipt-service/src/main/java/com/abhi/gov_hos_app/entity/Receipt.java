package com.abhi.gov_hos_app.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="receipt")
@ToString(exclude = {"problem"})
public class Receipt {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AA_RECEIPT_SEQ")
	@SequenceGenerator(name = "AA_RECEIPT_SEQ", sequenceName = "AA_RECEIPT_SEQ", allocationSize = 1)
	private Long receiptId;

	private String detail;
	private String barcode;
	private String drugDetail;
	private String usage;
	private String deliveryDate;
	private int status;

	@Column(name = "problem_id")
	private Long problemId;
}