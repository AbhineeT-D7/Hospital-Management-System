package com.abhi.gov_hos_app.util;

public class ApiPaths {
	private static final String BASE_PATH = "/api";
	private static final String PATIENT_PATH = "/patient";
	private static final String PROBLEM_PATH = "/problem";
	private static final String RECEIPT_PATH = "/receipt";
	private static final String STAFF_PATH = "/staff";
	
	public static final class PatientCtrl {
		public static final String CTRL = BASE_PATH + PATIENT_PATH;
	}
	public static final class ProblemCtrl {
		public static final String CTRL = BASE_PATH + PROBLEM_PATH;
	}
	public static final class ReceiptCtrl {
		public static final String CTRL = BASE_PATH + RECEIPT_PATH;
	}
	public static final class staffCtrl {
		public static final String CTRL = BASE_PATH + STAFF_PATH;
	}
}
