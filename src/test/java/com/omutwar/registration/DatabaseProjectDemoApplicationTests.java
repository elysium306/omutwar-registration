package com.omutwar.registration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DatabaseProjectDemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("If the application context starts, this test passes.");
	}
}
