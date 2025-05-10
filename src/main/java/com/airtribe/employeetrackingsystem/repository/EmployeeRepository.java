package com.airtribe.employeetrackingsystem.repository;

import com.airtribe.employeetrackingsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {
    //Customer query to find employees wh belong to a project using project id
    List<Employee> findByProjects_Id(Long projectId);
}
