package com.example.employeecrud.controller;

import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.service.interfaces.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> list=employeeService.getAllEmployee();
        if(list.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable int id){
        Employee employee = employeeService.getEmployeeById(id);
        if(employee==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(employee));
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        try{
           employeeService.createEmployee(employee);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp, @PathVariable int id){
        try{
            employeeService.updateEmployee(emp, id);
            return ResponseEntity.ok().body(emp);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @PatchMapping("/{id}")
    public Employee updateEmployeeFields(@PathVariable int id,@RequestBody Map<String, Object> field){
        return employeeService.updateEmployeeByFields(id, field);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmp(@PathVariable int id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
