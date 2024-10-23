package com.example.employeecrud.service;

import com.example.employeecrud.entity.Employee;
import com.example.employeecrud.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@SpringBootTest
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void getAllEmpoyeeTest(){
        List<Employee> emp=List.of(
                new Employee(1, "John", "Python Dev", 65000),
                new Employee(2, "Robert", "Java Dev", 45000));
        when(employeeRepository.findAll()).thenReturn(emp);
        List<Employee> res= employeeService.getAllEmployee();
        Assertions.assertEquals(2, res.size());
    }

    @Test
    void getEmployeeById_Test(){
        Employee emp= new Employee(1, "John", "Java Dev", 56000);
        when(employeeRepository.findById(1)).thenReturn(emp);
        Employee res= employeeService.getEmployeeById(1);
        assertNotNull(res);
        Assertions.assertEquals("John", res.getName());
    }

    @Test
    void testCreateEmployee() {
        Employee employee = new Employee(1, "John", "Java Dev", 56000);
        when(employeeRepository.save(employee)).thenReturn(employee);
        Employee result = employeeService.createEmployee(employee);
        assertNotNull(result);
        Assertions.assertEquals("John", result.getName());

    }

    @Test
    void testDeleteEmployee() {
        int employeeId = 1;
        employeeService.deleteEmployee(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void testUpdateEmployee() {
        Employee employee = new Employee(1, "John","Java Dev", 56000);
        when(employeeRepository.save(employee)).thenReturn(employee);
        employeeService.updateEmployee(employee, 1);
        verify(employeeRepository, times(1)).save(employee);
        Assertions.assertEquals(1, employee.getId());
    }

}
