package com.project.LibraryManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.entity.User;
import com.project.LibraryManagementSystem.model.Role;
import com.project.LibraryManagementSystem.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 🔹 Register a new user (Defaults to USER role)
	public User registerUser(String username, String password) {
		if (userRepository.existsByUsername(username)) {
			throw new RuntimeException("Username already exists!");
		}

		User newUser = new User(username, passwordEncoder.encode(password), Role.USER);
		return userRepository.save(newUser);
	}

	// 🔹 Register an admin (Should only be called manually)
	public User registerAdmin(String username, String password) {
		if (userRepository.existsByUsername(username)) {
			throw new RuntimeException("Username already exists!");
		}

		User newAdmin = new User(username, passwordEncoder.encode(password), Role.ADMIN);
		return userRepository.save(newAdmin);
	}

	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean passwordMatches(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}