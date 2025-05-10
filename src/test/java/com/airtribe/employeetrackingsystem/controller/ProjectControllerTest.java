package com.airtribe.employeetrackingsystem.controller;

import com.airtribe.employeetrackingsystem.entity.Project;
import com.airtribe.employeetrackingsystem.model.ProjectModel;
import com.airtribe.employeetrackingsystem.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;


@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService service;

    @Test
    void testSaveProject() throws Exception {
        ProjectModel project = new ProjectModel("Test Project",20000.00);
        Mockito.when(service.saveProject(any(ProjectModel.class))).thenReturn("Project saved");
        String jsonPayload = """
				{
				  "projectName": "Test Project 1",
				  "budget": 20000
				}
				""";

        // Act & Assert
        mockMvc.perform(post("/project").content(jsonPayload))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetAllProjects() throws Exception {
        Project project = new Project(1L,"Test Project", null,10000.0,null);
        Mockito.when(service.fetchAllProjects()).thenReturn(List.of(project));

        // Act & Assert
        mockMvc.perform(get("/projects"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testDeleteProject() throws Exception {
        Mockito.when(service.deleteProjectById(1L)).thenReturn("Project deleted");

        // Act & Assert
        mockMvc.perform(delete("/projects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateProject() throws Exception {
        //Project project = new Project(1L, "Project B", null, 2000.00 , null);
        Mockito.when(service.updateProject(eq(1L),any(Project.class))).thenReturn("Project Updated");
        String jsonPayload = """
				{
				  "projectName": "Test Project Updated",
				  "budget": 20000
				}
				""";
        // Act & Assert
        mockMvc.perform(put("/projects/1")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
