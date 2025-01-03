package com.airtribe.employeetrackingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DB relations:
 *  Department - Employee : One to Many
 *  Department - Project : One to Many
 */

@Entity
@Data
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String departmentName;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employeesInDepartment;

    // one department can have multiple projects
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Project> projectsInDepartment;

}
