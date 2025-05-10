package com.airtribe.employeetrackingsystem.service;

import com.airtribe.employeetrackingsystem.entity.Department;
import com.airtribe.employeetrackingsystem.entity.Project;
import com.airtribe.employeetrackingsystem.model.DepartmentModel;
import com.airtribe.employeetrackingsystem.model.ProjectModel;
import com.airtribe.employeetrackingsystem.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private DepartmentRepository repository;

    @InjectMocks
    private DepartmentService service;

    @Test
    void testGetAllDepartmentsService() {
        List<Department> departments = new ArrayList<>();
        Department departmentA = new Department(1L, "IT", null, null);
        Department departmentB = new Department(2L, "HR", null, null);
        departments.add(departmentA);
        departments.add(departmentB);
        when(repository.findAll()).thenReturn(departments);
        List<Department> result = service.fetchAllDepartments();
        assertEquals(2, result.size());
    }

    @Test
    void testSaveDepartmentService() {
        // Arrange
        DepartmentModel departmentModel = new DepartmentModel();
        departmentModel.setDepartmentName("Engineering");
        departmentModel.setProjectsInDepartment(List.of());

        when(repository.save(any(Department.class))).thenAnswer(invocation -> invocation.getArgument(0));
        String result = service.saveDepartment(departmentModel);

        verify(repository, never()).findAllById(anyList());
        verify(repository, times(1)).save(any(Department.class));
        verify(repository, never()).saveAll(anyList());
        assertEquals("Department saved successfully", result);
    }

    @Test
    void testDeleteDepartmentById_Success() {
        Long departmentId = 1L;
        Department department = new Department();
        department.setId(departmentId);
        department.setDepartmentName("Engineering");

        when(repository.findById(departmentId)).thenReturn(Optional.of(department));
        String result = service.deleteDepartmentById(departmentId);

        verify(repository, times(1)).findById(departmentId);
        verify(repository, times(1)).delete(department);
        assertEquals("Department deleted successfully", result);
    }

    @Test
    void testUpdateDepartment_Success() {
        Long departmentId = 1L;
        Department existingDepartment = new Department();
        existingDepartment.setId(departmentId);
        existingDepartment.setDepartmentName("Old Name");

        Department updatedDepartment = new Department();
        updatedDepartment.setDepartmentName("New Name");

        when(repository.findById(departmentId)).thenReturn(Optional.of(existingDepartment));
        when(repository.save(existingDepartment)).thenReturn(existingDepartment);
        String result = service.updateDepartment(departmentId, updatedDepartment);

        verify(repository, times(1)).findById(departmentId);
        verify(repository, times(1)).save(existingDepartment);
        assertEquals("Department updated successfully", result);

        assertEquals("New Name", existingDepartment.getDepartmentName());
    }
}
