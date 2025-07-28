package com.airtribe.employeetrackingsystem.service;

import com.airtribe.employeetrackingsystem.entity.Department;
import com.airtribe.employeetrackingsystem.entity.Project;
import com.airtribe.employeetrackingsystem.model.DepartmentModel;
import com.airtribe.employeetrackingsystem.repository.DepartmentRepository;
import com.airtribe.employeetrackingsystem.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    public String saveDepartment(@Valid DepartmentModel department) {
        Department newDepartment = new Department();
        newDepartment.setDepartmentName(department.getDepartmentName());
        // Fetch all projects with help of projectIds in Department Model
        List<Project> projects = projectRepository.findAllById(
                department.getProjectsInDepartment()
                        .stream()
                        .map(Project::getId)
                        .collect(Collectors.toList())
        );
        // Assign department to each project
        for (Project project : projects) {
            project.setDepartment(newDepartment);
        }
        // Save Department object first as project needs department to be saved
        newDepartment.setProjectsInDepartment(projects);
        departmentRepository.save(newDepartment);
        projectRepository.saveAll(projects);
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

    public String getBudgetForProjectsInDepartment(Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        double budget = 0;
        if (department.isPresent()) {
            for(Project p : department.get().getProjectsInDepartment()){
                budget += p.getBudget();
            }
        }
        else {
            return "Department not found";
        }
        return String.format("%.2f", budget);
    }
}
