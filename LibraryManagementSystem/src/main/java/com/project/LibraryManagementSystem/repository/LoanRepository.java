package com.project.LibraryManagementSystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.LibraryManagementSystem.entity.Book;
import com.project.LibraryManagementSystem.entity.Loan;
import com.project.LibraryManagementSystem.entity.User;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
	List<Loan> findByUserId(Long userId);

	List<Loan> findByBookId(Long bookId);

	boolean existsByBookIdAndReturnDateIsNull(Long bookId);

	boolean existsByBookAndReturnDateIsNull(Book book); // ✅ Add this method to check if a book is already borrowed

	List<Loan> findByUser(User user);

	List<Loan> findByReturnDateIsNullAndBorrowDateBefore(LocalDate overdueDate);
}
