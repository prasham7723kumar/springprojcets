package com.project.LibraryManagementSystem.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagementSystem.entity.Loan;
import com.project.LibraryManagementSystem.service.LoanService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private LoanService loanService;

	// 🔹 View all loans (Only Admins)
	@GetMapping("/loans")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getAllLoans() {
		try {
			List<Loan> loans = loanService.getAllLoans();
			return ResponseEntity.ok(loans);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("error", e.getMessage()));
		}
	}

	// 🔹 View overdue books (Only Admins)
	@GetMapping("/overdue-books")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> getOverdueBooks() {
		try {
			List<Loan> overdueLoans = loanService.getOverdueLoans();
			return ResponseEntity.ok(overdueLoans);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("error", e.getMessage()));
		}
	}
}