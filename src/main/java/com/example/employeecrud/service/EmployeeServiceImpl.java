package com.example.employeecrud.service;

import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.exception.EmployeeNotFoundException;
import com.example.employeecrud.repository.EmployeeRepository;
import com.example.employeecrud.service.interfaces.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployee() {
        if (employeeRepository.findAll().isEmpty()) {
            log.error("List is Empty");
            throw new EmployeeNotFoundException("Employee list is Empty");
        }
        log.info("Get all the Employee's");
        return this.employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        if (employeeRepository.findById(id) == null) {
            log.error("ID is not found");
            throw new EmployeeNotFoundException("Employee Not found with this Id");
        } else {
            log.info("Found right employee");
            return employeeRepository.findById(id);
        }
    }

    @Override
    public Employee createEmployee(Employee emp) {
        return employeeRepository.save(emp);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployee(Employee emp, int id) {
        emp.setId(id);
        employeeRepository.save(emp);
    }

    @Override
    public Employee updateEmployeeByFields(int id, Map<String, Object> field) {
        Employee existingEmp = employeeRepository.findById(id);
        if (existingEmp != null) {
            field.forEach((key, value) -> {
                Field field1 = ReflectionUtils.findField(Employee.class, key);
                field1.setAccessible(true);
                ReflectionUtils.setField(field1, existingEmp, value);
            });
            log.info("Desired field is updated");
            return employeeRepository.save(existingEmp);
        }
        log.error("Id does not exist");
        return null;
    }
}
