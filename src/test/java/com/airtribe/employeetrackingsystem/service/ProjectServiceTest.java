package com.airtribe.employeetrackingsystem.service;

import com.airtribe.employeetrackingsystem.entity.Project;
import com.airtribe.employeetrackingsystem.model.ProjectModel;
import com.airtribe.employeetrackingsystem.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {
    @Mock
    private ProjectRepository repository;

    @InjectMocks
    private ProjectService service;

    @Test
    void testGetAllProjectsService() {
        List<Project> projects = new ArrayList<>();
        Project projectA = new Project(1L, "Project A", null,10000.0,null);
        Project projectB = new Project(1L, "Project B", null,15000.0,null);
        projects.add(projectA);
        projects.add(projectB);
        when(repository.findAll()).thenReturn(projects);
        List<Project> result = service.fetchAllProjects();
        assertEquals(2, result.size());
    }

    @Test
    void testSaveProjectService() {
        ProjectModel projectModel = new ProjectModel();
        projectModel.setProjectName("New Project");
        projectModel.setBudget(10000.0);

        Project projectEntity = new Project();
        projectEntity.setProjectName(projectModel.getProjectName());
        projectEntity.setBudget(projectModel.getBudget());

        String result = service.saveProject(projectModel);

        verify(repository, times(1)).save(any(Project.class));
        assertEquals("Project saved", result);
    }

    @Test
    void testDeleteProjectService() {
        // Arrange
        Long projectId = 1L;
        Project project = new Project();
        project.setId(projectId);

        when(repository.findById(projectId)).thenReturn(Optional.of(project));

        // Act
        String result = service.deleteProjectById(projectId);

        // Assert
        verify(repository, times(1)).findById(projectId); // Verify findById is called
        verify(repository, times(1)).delete(project); // Verify delete is called
        assertEquals("Project deleted", result); // Assert the return value
    }

    @Test
    void testUpdateProject_ProjectExists() {
        Long projectId = 1L;
        Project existingProject = new Project();
        existingProject.setId(projectId);
        existingProject.setProjectName("Old Project");

        Project updatedProject = new Project();
        updatedProject.setProjectName("New Project");

        when(repository.findById(projectId)).thenReturn(Optional.of(existingProject));

        String result = service.updateProject(projectId, updatedProject);

        verify(repository, times(1)).findById(projectId);
        verify(repository, times(1)).save(existingProject);
        assertEquals("New Project", existingProject.getProjectName());
        assertEquals("Project Updated", result);
    }

    @Test
    void testUpdateProject_ProjectDoesNotExist() {
        Long projectId = 1L;
        Project updatedProject = new Project();
        updatedProject.setProjectName("New Project");

        when(repository.findById(projectId)).thenReturn(Optional.empty());

        String result = service.updateProject(projectId, updatedProject);

        verify(repository, times(1)).findById(projectId);
        verify(repository, never()).save(any(Project.class));
        assertEquals("Project not found", result);
    }

}
