package com.myapp.springjpa01.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.springjpa01.model.Employee01;
import com.myapp.springjpa01.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployController {
	@Autowired
	EmployeeRepository employeeRepository;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/employeess")
	public Employee01 createEmp(@RequestBody Employee01 employee) {
		return employeeRepository.save(employee);
	}

	@GetMapping("/employeeget")
	public List<Employee01> getEmployee01() {
		return employeeRepository.findAll();
	}

	@PutMapping("/employeess/{id}")
	public ResponseEntity<Employee01> updateEmployee(@PathVariable long id, @RequestBody Employee01 employeeDetails)
			throws ResourceNotFoundException {
		Employee01 employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not found for this id : " + id));

		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLasttName(employeeDetails.getLasttName());
		employee.setEmail(employeeDetails.getEmail());

		final Employee01 updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);

	}

	@DeleteMapping("/employeess/{id}")
	ResponseEntity<Map<String, Boolean>> deleteemployee(@PathVariable long id) throws ResourceNotFoundException {
		Employee01 employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee Not Found" + id));

		// now delete process
		employeeRepository.delete(employee);

		// After deleting , now get the response
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return ResponseEntity.ok(response);
	}
}
