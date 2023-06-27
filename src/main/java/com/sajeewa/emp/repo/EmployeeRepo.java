package com.sajeewa.emp.repo;

import com.sajeewa.emp.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
}
