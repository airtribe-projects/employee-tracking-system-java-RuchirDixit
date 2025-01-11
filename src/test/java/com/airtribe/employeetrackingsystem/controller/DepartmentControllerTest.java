package com.airtribe.employeetrackingsystem.controller;

import com.airtribe.employeetrackingsystem.entity.Department;
import com.airtribe.employeetrackingsystem.model.DepartmentModel;
import com.airtribe.employeetrackingsystem.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService service;

    @Test
    void testSaveDepartment() throws Exception {
        Mockito.when(service.saveDepartment(any(DepartmentModel.class))).thenReturn("Department saved successfully");
        String jsonPayload = """
                {
                    "departmentName" : "HR",
                    "projectsInDepartment":[
                {
                    "id":1
                },
                {
                    "id":2
                }
            ]
        }""";

        // Act & Assert
        mockMvc.perform(post("/department").content(jsonPayload))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetAllDepartments() throws Exception {
        Department department = new Department(1L,"Human Resources",null,null);
        Mockito.when(service.fetchAllDepartments()).thenReturn(List.of(department));

        // Act & Assert
        mockMvc.perform(get("/departments"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testDeleteDepartment() throws Exception {
        Mockito.when(service.deleteDepartmentById(1L)).thenReturn("Department deleted successfully");

        // Act & Assert
        mockMvc.perform(delete("/department/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateDepartment() throws Exception {
        Mockito.when(service.updateDepartment(eq(1L),any(Department.class))).thenReturn("Department updated successfully");
        String jsonPayload = """
                {
                    "departmentName" : "HR (Human Resource)"
                }
        		""";
        // Act & Assert
        mockMvc.perform(put("/department/1")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
