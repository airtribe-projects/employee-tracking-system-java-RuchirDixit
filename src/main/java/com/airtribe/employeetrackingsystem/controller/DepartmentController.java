package com.airtribe.employeetrackingsystem.controller;


import com.airtribe.employeetrackingsystem.entity.Department;
import com.airtribe.employeetrackingsystem.model.DepartmentModel;
import com.airtribe.employeetrackingsystem.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<String> saveDepartment(@Valid @RequestBody DepartmentModel department) {
        String response = departmentService.saveDepartment(department);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> response = departmentService.fetchAllDepartments();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        String response = departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long id,@Valid @RequestBody Department updatedDepartment) {
        String response = departmentService.updateDepartment(id,updatedDepartment);
        return ResponseEntity.ok(response);
    }
}
