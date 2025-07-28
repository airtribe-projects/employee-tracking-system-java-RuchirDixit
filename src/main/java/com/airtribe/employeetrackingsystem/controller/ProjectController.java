package com.airtribe.employeetrackingsystem.controller;

import com.airtribe.employeetrackingsystem.entity.Project;
import com.airtribe.employeetrackingsystem.model.ProjectModel;
import com.airtribe.employeetrackingsystem.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<String> saveProject(@Valid @RequestBody ProjectModel project) {
        String response = projectService.saveProject(project);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> response = projectService.fetchAllProjects();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/project/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        String response = projectService.deleteProjectById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/project/{id}")
    public ResponseEntity<String> updateProject(@PathVariable Long id,@Valid @RequestBody Project updatedProject) {
        String response = projectService.updateProject(id,updatedProject);
        return ResponseEntity.ok(response);
    }
}
