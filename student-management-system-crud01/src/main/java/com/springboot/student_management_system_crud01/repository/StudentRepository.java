package com.springboot.student_management_system_crud01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.student_management_system_crud01.Entity.Student;

//@Repository // letting kno its Repository rather than default // not needed on service layer
public interface StudentRepository extends JpaRepository<Student,Long> {
  // <entity name , typeof primary key> // type of primary key cannot be primitive
}
