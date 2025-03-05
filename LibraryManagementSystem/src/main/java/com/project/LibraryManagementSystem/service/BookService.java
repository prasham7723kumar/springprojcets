package com.project.LibraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.LibraryManagementSystem.entity.Book;
import com.project.LibraryManagementSystem.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	// Get all books
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	// Get book by ID
	public Optional<Book> getBookById(Long id) {
		return bookRepository.findById(id);
	}

	// Add a new book
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	// Update a book
	public Book updateBook(Long id, Book updatedBook) {
		if (bookRepository.existsById(id)) {
			updatedBook.setId(id);
			return bookRepository.save(updatedBook);
		}
		return null;
	}

	// Delete a book
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
}