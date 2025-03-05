package com.project.LibraryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.LibraryManagementSystem.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Additional custom queries can be added here if needed
}
