package com.airtribe.employeetrackingsystem.repository;

import com.airtribe.employeetrackingsystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {}
