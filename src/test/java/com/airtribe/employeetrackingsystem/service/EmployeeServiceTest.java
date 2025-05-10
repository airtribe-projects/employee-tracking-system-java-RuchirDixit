package com.airtribe.employeetrackingsystem.service;

import com.airtribe.employeetrackingsystem.entity.Department;
import com.airtribe.employeetrackingsystem.entity.Employee;
import com.airtribe.employeetrackingsystem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository repository;

    @InjectMocks
    private EmployeeService service;

    @Test
    void testSaveEmployee_Success() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("John Doe");
        employee.setDepartment(new Department());

        when(repository.save(employee)).thenReturn(employee);
        String result = service.saveEmployee(employee);


        verify(repository, times(1)).save(employee);
        assertEquals("Employee saved successfully", result);
    }

    @Test
    void testFetchAllEmployees_Success() {
        List<Employee> employees = new ArrayList<>();
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setName("John Doe");
        employee1.setDepartment(new Department());

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setName("Jane Smith");
        employee2.setDepartment(new Department());

        employees.add(employee1);
        employees.add(employee2);

        when(repository.findAll()).thenReturn(employees);
        List<Employee> result = service.fetchAllEmployees();
        verify(repository, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Smith", result.get(1).getName());
    }

    @Test
    void testUpdateEmployee_Success() {
        Long employeeId = 1L;
        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setName("John Doe");
        existingEmployee.setEmail("john.doe@example.com");
        existingEmployee.setDepartment(new Department());
        existingEmployee.setRoles(new ArrayList<>());
        existingEmployee.setProjects(new ArrayList<>());

        Employee updatedEmployee = new Employee();
        updatedEmployee.setName("John Smith");
        updatedEmployee.setEmail("john.smith@example.com");
        updatedEmployee.setDepartment(new Department());
        updatedEmployee.setRoles(new ArrayList<>());
        updatedEmployee.setProjects(new ArrayList<>());

        when(repository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(repository.save(existingEmployee)).thenReturn(existingEmployee);
        String result = service.updateEmployee(employeeId, updatedEmployee);

        verify(repository, times(1)).findById(employeeId);
        verify(repository, times(1)).save(existingEmployee);
        assertEquals("Employee updated successfully", result);

        assertEquals("John Smith", existingEmployee.getName());
        assertEquals("john.smith@example.com", existingEmployee.getEmail());
    }

    @Test
    void testDeleteEmployee_Success() {
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setName("John Doe");

        when(repository.findById(employeeId)).thenReturn(Optional.of(employee));
        String result = service.deleteEmployee(employeeId);

        verify(repository, times(1)).findById(employeeId);
        verify(repository, times(1)).delete(employee);
        assertEquals("Employee deleted successfully", result);
    }
}
