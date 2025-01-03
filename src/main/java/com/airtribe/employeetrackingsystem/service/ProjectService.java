package com.airtribe.employeetrackingsystem.service;

import com.airtribe.employeetrackingsystem.entity.Project;
import com.airtribe.employeetrackingsystem.model.ProjectModel;
import com.airtribe.employeetrackingsystem.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public String saveProject(ProjectModel project) {
        Project newProject = new Project();
        newProject.setProjectName(project.getProjectName());
        newProject.setBudget(project.getBudget());
        projectRepository.save(newProject);
        return "Project saved";
    }

    public List<Project> fetchAllProjects() {
        return projectRepository.findAll();
    }

    public String deleteProjectById(Long id) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()) {
            projectRepository.delete(project.get());
        }
        else{
            return "Project not found";
        }
        return "Project deleted";
    }

    public String updateProject(Long id, Project updatedProject) {
        Optional<Project> project = projectRepository.findById(id);
        if(project.isPresent()) {
            project.get().setProjectName(updatedProject.getProjectName());
            projectRepository.save(project.get());
        }
        else{
            return "Project not found";
        }
        return "Project Updated";
    }
}
