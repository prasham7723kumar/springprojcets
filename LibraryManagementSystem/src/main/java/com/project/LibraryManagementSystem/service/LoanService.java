package com.project.LibraryManagementSystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.entity.Book;
import com.project.LibraryManagementSystem.entity.Loan;
import com.project.LibraryManagementSystem.entity.User;
import com.project.LibraryManagementSystem.repository.BookRepository;
import com.project.LibraryManagementSystem.repository.LoanRepository;
import com.project.LibraryManagementSystem.repository.UserRepository;

@Service
public class LoanService {

	@Autowired
	private LoanRepository loanRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Borrow a book only if it is available.
	 */
	public Loan borrowBook(Long userId, Long bookId) throws Exception {
		Optional<User> user = userRepository.findById(userId);
		Optional<Book> book = bookRepository.findById(bookId);

		if (user.isEmpty()) {
			throw new Exception("User not found");
		}

		if (book.isEmpty()) {
			throw new Exception("Book not found");
		}

		// Check if the book is already borrowed
		if (loanRepository.existsByBookAndReturnDateIsNull(book.get())) {
			throw new Exception("Book is already borrowed by another user");
		}

		// Create new loan entry
		Loan loan = new Loan(user.get(), book.get(), LocalDate.now());
		return loanRepository.save(loan);
	}

	/**
	 * Return a book only if it was borrowed.
	 */
	public Loan returnBook(Long loanId) throws Exception {
		Optional<Loan> loan = loanRepository.findById(loanId);

		if (loan.isEmpty()) {
			throw new Exception("Loan record not found");
		}

		Loan loanRecord = loan.get();
		if (loanRecord.getReturnDate() != null) {
			throw new Exception("Book has already been returned");
		}

		loanRecord.setReturnDate(LocalDate.now());
		return loanRepository.save(loanRecord);
	}

	/**
	 * Get the list of books borrowed by a user.
	 */
	public List<Loan> getUserLoans(Long userId) throws Exception {
		if (!userRepository.existsById(userId)) {
			throw new Exception("User not found");
		}
		return loanRepository.findByUserId(userId);
	}

	public List<Loan> getAllLoans() {
		return loanRepository.findAll();

	}

	public List<Loan> getOverdueLoans() {
		return loanRepository.findByReturnDateIsNullAndBorrowDateBefore(LocalDate.now().minusDays(14)); // 14 days limit
	}
}
