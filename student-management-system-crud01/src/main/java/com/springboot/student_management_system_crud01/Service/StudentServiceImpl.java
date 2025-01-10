package com.springboot.student_management_system_crud01.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.student_management_system_crud01.Entity.Student;
import com.springboot.student_management_system_crud01.repository.StudentRepository;

@Service // letting it to be known as a service
public class StudentServiceImpl implements StudentService {
	// service implementation of StudentService

	// adding dependency injection of repository
	private StudentRepository studentRepository;

	// constructor based injection
	public StudentServiceImpl(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll(); // findAll() returns list of the <Entity>
	}

	@Override
	public Student getStudentById(Long id) { // get a reference student by id
		return studentRepository.getReferenceById(id);
	}

	@Override
	public Student addStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

	@Override
	public Student updateStudent(Student student) { // edit and update student if exist otherwise add new
		return studentRepository.save(student);
	}

}
