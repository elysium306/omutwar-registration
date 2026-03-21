package com.omutwar.registration.constraints;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.repository.UserRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ActiveProfiles("test")
class UserConstraintValidationTest {

	@Autowired
	private UserRepository repo;

	@Test
	void testInvalidEmailFails() {
		User u = new User();
		u.setEmail("not-an-email");
		u.setPasswordHash("hash123");

		assertThrows(ConstraintViolationException.class, () -> repo.saveAndFlush(u));
	}
}