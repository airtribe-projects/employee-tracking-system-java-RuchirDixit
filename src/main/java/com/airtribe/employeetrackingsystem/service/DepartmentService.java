package com.airtribe.employeetrackingsystem.service;

import com.airtribe.employeetrackingsystem.entity.Department;
import com.airtribe.employeetrackingsystem.model.DepartmentModel;
import com.airtribe.employeetrackingsystem.repository.DepartmentRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;

    public String saveDepartment(@Valid DepartmentModel department) {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName(department.getDepartmentName());
        departmentRepository.save(newDepartment);
        return "Department saved successfully";
    }

    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

    public String deleteDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
        }
        else{
            return "Department not found";
        }
        return "Department deleted successfully";
    }

    public String updateDepartment(Long id,Department updatedDepartment) {
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            department.get().setDepartmentName(updatedDepartment.getDepartmentName());
            departmentRepository.save(department.get());
        }
        else{
            return "Department not found";
        }
        return "Department updated successfully";
    }
}
