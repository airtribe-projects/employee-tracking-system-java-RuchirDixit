package com.airtribe.employeetrackingsystem.model;

import com.airtribe.employeetrackingsystem.entity.Project;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentModel {
    private String departmentName;
    private List<Project> projectsInDepartment;
}
