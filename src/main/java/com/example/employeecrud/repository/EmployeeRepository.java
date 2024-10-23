package com.example.employeecrud.repository;

import com.example.employeecrud.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
     Employee findById(int id);
}
