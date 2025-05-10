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
 *  Project - Employee : Many to Many
 *  Project - Department : Many to One
 */

@Entity
@Data
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String projectName;


    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private List<Employee> employees;

    private Double budget;

    // every project belong to only one department
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;

    public Project(){

    }

    public Project(Long id, String projectName, List<Employee> employees, Double budget, Department department) {
        this.id = id;
        this.projectName = projectName;
        this.employees = employees;
        this.budget = budget;
        this.department = department;
    }
}
