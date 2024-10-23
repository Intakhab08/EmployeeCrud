package com.example.employeecrud.service.interfaces;

import com.example.employeecrud.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(int id);
    public Employee createEmployee(Employee emp);
    public void deleteEmployee(int id);
    public void updateEmployee(Employee emp, int id);
    public Employee updateEmployeeByFields(int id, Map<String, Object> field);
}
