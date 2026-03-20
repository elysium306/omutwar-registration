package com.omutwar.registration.service;

import com.omutwar.registration.domain.User;
import com.omutwar.registration.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

	private final UserRepository repo = new UserRepository();

	public long registerUser(User user) throws SQLException {
		return repo.insert(user);
	}

	public Optional<User> getUserById(long id) throws SQLException {
		return repo.findById(id);
	}

	public Optional<User> getUserByEmail(String email) throws SQLException {
		return repo.findByEmail(email);
	}

	public boolean updateUser(User user) throws SQLException {
		return repo.update(user);
	}

	public boolean deleteUser(long id) throws SQLException {
		return repo.deleteById(id);
	}

	public List<User> listUsers(int limit, int offset) throws SQLException {
		return repo.findAll(limit, offset);
	}
}