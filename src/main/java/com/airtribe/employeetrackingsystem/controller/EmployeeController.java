package com.airtribe.employeetrackingsystem.controller;

import com.airtribe.employeetrackingsystem.entity.Employee;
import com.airtribe.employeetrackingsystem.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.fetchAllEmployees();
        return ResponseEntity.ok(employees);
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
}
