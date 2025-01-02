package com.airtribe.employeetrackingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * DB relations:
 *  Project - Employee : Many to Many
 *  Project - Department : Many to One
 */

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String projectName;


    @ManyToMany(mappedBy = "projects")
    @JsonIgnore
    private List<Employee> employees;

    // every project belong to only one department
    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnore
    private Department department;
}
