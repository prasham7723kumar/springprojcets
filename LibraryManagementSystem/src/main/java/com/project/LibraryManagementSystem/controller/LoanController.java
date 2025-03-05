package com.project.LibraryManagementSystem.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagementSystem.entity.Loan;
import com.project.LibraryManagementSystem.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

	@Autowired
	private LoanService loanService;

	/**
	 * Borrow a book (POST /loans/borrow)
	 */
	@PostMapping("/borrow")
	public ResponseEntity<?> borrowBook(@RequestParam Long userId, @RequestParam Long bookId) {
		try {
			Loan loan = loanService.borrowBook(userId, bookId);
			return ResponseEntity.ok(loan);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("error", e.getMessage()));
		}
	}

	/**
	 * Return a book (POST /loans/return)
	 */
	@PostMapping("/return")
	public ResponseEntity<?> returnBook(@RequestParam Long loanId) {
		try {
			Loan loan = loanService.returnBook(loanId);
			return ResponseEntity.ok(loan);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("error", e.getMessage()));
		}
	}

	/**
	 * Get a user's borrowed books (GET /loans/user/{userId})
	 */
	@GetMapping("/user/{userId}")
	public ResponseEntity<?> getUserLoans(@PathVariable Long userId) {
		try {
			List<Loan> loans = loanService.getUserLoans(userId);
			return ResponseEntity.ok(loans);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(Collections.singletonMap("error", e.getMessage()));
		}
	}
}