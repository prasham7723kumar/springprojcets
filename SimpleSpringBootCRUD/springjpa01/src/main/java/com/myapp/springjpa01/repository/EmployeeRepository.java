package com.myapp.springjpa01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.springjpa01.model.Employee01;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee01, Long>{

}
