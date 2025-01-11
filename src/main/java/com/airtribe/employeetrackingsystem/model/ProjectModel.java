package com.airtribe.employeetrackingsystem.model;

import lombok.Data;

@Data
public class ProjectModel {
    private String projectName;
    private Double budget;

    public ProjectModel(){

    }


    public ProjectModel(String projectName, Double budget) {
        this.projectName = projectName;
        this.budget = budget;
    }
}
