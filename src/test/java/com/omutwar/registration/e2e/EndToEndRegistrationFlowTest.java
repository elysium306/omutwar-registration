package com.omutwar.registration.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.omutwar.registration.dto.LoginResponse;
import com.omutwar.registration.request.LoginRequest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class EndToEndRegistrationFlowTest {

	@Autowired
	private TestRestTemplate rest;

	@Test
	void testUserRegistrationAndLoginFlow() {
		// 1. Register user
		var registerReq = new LoginRequest();
		registerReq.email = "test@example.com";
		registerReq.password = "password123";

		ResponseEntity<Void> regResp = rest.postForEntity("/users/register", registerReq, Void.class);

		assertThat(regResp.getStatusCode()).isEqualTo(HttpStatus.OK);

		// 2. Login
		ResponseEntity<LoginResponse> loginResp = rest.postForEntity("/auth/login", registerReq, LoginResponse.class);

		assertThat(loginResp.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(loginResp.getBody().token).isNotBlank();
	}
}