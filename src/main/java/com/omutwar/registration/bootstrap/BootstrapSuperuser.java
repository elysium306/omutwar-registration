package com.omutwar.registration.bootstrap;

import com.omutwar.registration.config.BootstrapSuperuserProperties;
import com.omutwar.registration.domain.User;
import com.omutwar.registration.domain.User.UserStatus;
import com.omutwar.registration.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BootstrapSuperuser {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final BootstrapSuperuserProperties props;

	public BootstrapSuperuser(UserRepository userRepository, PasswordEncoder passwordEncoder,
			BootstrapSuperuserProperties props) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.props = props;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void init() {
		String email = props.getEmail();
		String password = props.getPassword();

		if (!userRepository.existsByEmail(email)) {
			User u = new User();
			u.setEmail(email);
			u.setFirstName(props.getFirstName());
			u.setLastName(props.getLastName());
			u.setStatus(UserStatus.ACTIVE);
			u.setPasswordHash(passwordEncoder.encode(password));

			userRepository.save(u);
			System.out.println("Bootstrap superuser created: " + email);
		} else {
			System.out.println("Bootstrap superuser already exists: " + email);
		}
	}
}
