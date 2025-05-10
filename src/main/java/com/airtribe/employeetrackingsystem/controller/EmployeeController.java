package com.airtribe.employeetrackingsystem.controller;

import com.airtribe.employeetrackingsystem.entity.Employee;
import com.airtribe.employeetrackingsystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<String> saveEmployee(@Valid @RequestBody Employee employeeModel) {
        String response = employeeService.saveEmployee(employeeModel);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.fetchAllEmployees();
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<String> updateEmployee(@PathVariable Long id, @Valid @RequestBody Employee updatedEmployee) {
        String response = employeeService.updateEmployee(id,updatedEmployee);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        String response = employeeService.deleteEmployee(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public List<Employee> searchEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Long departmentId) {
        return employeeService.search(firstName, email, departmentId);
    }

    //API to search employee not assigned to any project
    @GetMapping("/employee/unassigned")
    public ResponseEntity<List<Employee>> unassignedEmployeesToProjects() {
        List<Employee> employees = employeeService.fetchUnassignedEmployeesToProjects();
        return ResponseEntity.ok(employees);
    }


    //API to search employees working on specific project
    @GetMapping("/employee/project/{id}")
    public ResponseEntity<List<Employee>> getEmployeeProjects(@PathVariable Long id) {
        List<Employee> employees = employeeService.getEmployeeProjects(id);
        return ResponseEntity.ok(employees);
    }
}
