package com.project.LibraryManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.LibraryManagementSystem.entity.Book;
import com.project.LibraryManagementSystem.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

	@Autowired
	private BookService bookService;

	// Get all books
	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	// Get book by ID
	@GetMapping("/{id}")
	public Optional<Book> getBookById(@PathVariable Long id) {
		return bookService.getBookById(id);
	}

	// Add a new book
	@PostMapping
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	// Update a book
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
		return bookService.updateBook(id, book);
	}

	// Delete a book
	@DeleteMapping("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}
}