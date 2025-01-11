package com.airtribe.employeetrackingsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.JoinColumn;
import org.hibernate.validator.constraints.Length;

/**
 * DB relations:
 *  Employee - Project : Many to Many
 *  Employee - Department : Many to One
 */

@Entity
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String username;

    @NotNull
    @Length (min = 8)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "employee_roles",
    joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Roles> roles;


    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

    public Employee() {

    }

    public Employee(Long id, String name, String email, String username, String password, List<Roles> roles, Department department, List<Project> projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.department = department;
        this.projects = projects;
    }
}
