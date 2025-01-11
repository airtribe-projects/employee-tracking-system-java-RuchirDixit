package com.airtribe.employeetrackingsystem.controller;


import com.airtribe.employeetrackingsystem.entity.Employee;
import com.airtribe.employeetrackingsystem.service.EmployeeService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService service;

    @Test
    void testSaveEmployee() throws Exception {
        Mockito.when(service.saveEmployee(any(Employee.class))).thenReturn("Employee saved successfully");
        String jsonPayload = """
                {
                            "name" : "John D",
                            "email" : "johndoe@gmail.com",
                            "username" : "djohn",
                            "password" : "jddhfjdfdhfjdhf",
                            "projects" : [
                                {
                                    "id":1
                                }
                            ],
                            "department" : {
                                "id":1
                            }
                        }
        """;

        // Act & Assert
        mockMvc.perform(post("/employee").content(jsonPayload))
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetAllEmployees() throws Exception {
        Employee employee = new Employee(1L,"Jonny Dep","dJonny@gmail.com","djonny","jonnyjonnyyespapa123",null,null,null);
        Mockito.when(service.fetchAllEmployees()).thenReturn(List.of(employee));

        // Act & Assert
        mockMvc.perform(get("/employees"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testDeleteEmployee() throws Exception {
        Mockito.when(service.deleteEmployee(1L)).thenReturn("Employee deleted successfully");

        // Act & Assert
        mockMvc.perform(delete("/employee/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Mockito.when(service.updateEmployee(eq(1L),any(Employee.class))).thenReturn("Employee updated successfully");
        String jsonPayload = """
                {
                          "name" : "Dumbo",
                          "email" : "dumboo@gmail.com",
                          "username" : "jdumbo",
                          "password" : "jddhfjdfdhfjdhf",
                          "projects" : [
                              {
                                  "id":1
                              }
                          ],
                          "department" : {
                              "id":1
                          }
                      }
        		""";
        // Act & Assert
        mockMvc.perform(put("/employee/1")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
