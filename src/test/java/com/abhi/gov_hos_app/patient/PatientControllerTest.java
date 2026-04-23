package com.abhi.gov_hos_app.patient;

import com.abhi.gov_hos_app.entity.Patient;
import com.abhi.gov_hos_app.entity.enums.City;
import com.abhi.gov_hos_app.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PatientControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private PatientService patientService;

	private String getBaseUrl() {
		return "http://localhost:" + port + "/patient";
	}

	@Test
	public void testGetAllPatients() {
		webTestClient.get()
				.uri("http://localhost:" + port + "/patient")
				.exchange()
				.expectStatus().isOk();
	}
	@Test
	public void savePatientV1() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = sdf.parse("1998-01-01");

		Patient p1 = new Patient(
				1234567890L, "zipato", "zazula",
				"1234567890", dob, "Male",
				City.PRAYAGRAJ, "zipato@gmail.com", 1
		);

		webTestClient.post()
				.uri(getBaseUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(p1)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Patient.class)
				.consumeWith(response -> {
					Patient saved = response.getResponseBody();
					assertThat(saved).isNotNull();
					assertThat(saved.getPatientId()).isNotNull();
					assertThat(saved.getFirstName()).isEqualTo("zipato");
				});
	}

	@Test
	public void savePatientV2() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dob = sdf.parse("1998-1-1");

		Patient p1 = new Patient(
				1234567891L, "kamara", "tamara",
				"9875461230", dob, "Female",
				City.RAJKOT, "kamara.tamara@mynet.com", 1
		);

		webTestClient.post()
				.uri(getBaseUrl())
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(p1)
				.exchange()
				.expectStatus().isOk()
				.expectBody(Patient.class)
				.consumeWith(response -> {
					Patient saved = response.getResponseBody();
					assertThat(saved).isNotNull();
					assertThat(saved.getPatientId()).isNotNull();
					assertThat(saved.getFirstName()).isEqualTo("kamara");
				});

		Patient p2 = patientService.save(p1);
		assertThat(p2.getPatientId()).isNotNull();
	}


	}
