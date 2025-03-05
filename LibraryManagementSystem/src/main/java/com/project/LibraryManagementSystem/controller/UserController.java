package com.project.LibraryManagementSystem.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagementSystem.entity.User;
import com.project.LibraryManagementSystem.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public User registerUser(@RequestBody User user) {
		return userService.registerUser(user.getUsername(), user.getPassword());
	}

	@PostMapping("/register-admin")
	public User registerAdmin(@RequestBody User user) {
		return userService.registerAdmin(user.getUsername(), user.getPassword());
	}

	@GetMapping("/{username}")
	public User getUser(@PathVariable String username) {
		return userService.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		Optional<User> foundUser = userService.findByUsername(user.getUsername());

		if (foundUser.isPresent() && userService.passwordMatches(user.getPassword(), foundUser.get().getPassword())) {
			return ResponseEntity.ok("Login Successful!");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or Password");
		}
	}
}