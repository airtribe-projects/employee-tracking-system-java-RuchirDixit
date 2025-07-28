package com.airtribe.employeetrackingsystem.service;

import com.airtribe.employeetrackingsystem.entity.Department;
import com.airtribe.employeetrackingsystem.entity.Employee;
import com.airtribe.employeetrackingsystem.entity.Project;
import com.airtribe.employeetrackingsystem.entity.Roles;
import com.airtribe.employeetrackingsystem.enums.RolesEnum;
import com.airtribe.employeetrackingsystem.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import jakarta.persistence.criteria.Predicate;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    public String saveEmployee(EmployeeModel employee) {
//        Employee employeeEntity = new Employee();
//        String response;
//        if (employee != null) {
//            employeeEntity.setName(employee.getName());
//            employeeEntity.setEmail(employee.getEmail());
//            employeeEntity.setUsername(employee.getUsername());
//            // store encoded password
//            //employeeEntity.setPassword(passwordEncoder.encode(employee.getPassword()));
//            employeeEntity.setPassword(employee.getPassword());
//            // setting employee roles
//            List<Roles> employeeRoles = new ArrayList<>();
////            for(RolesModel rolesModel:employee.getRoles()){
////                Roles role = new Roles();
////                role.setRoleName(rolesModel);
////                role.setEmployeeList(List.of(employeeEntity));
////                employeeRoles.add(role);
////            }
//            employee.getRoles().forEach(role -> {
//                Roles role1 = new Roles();
//                role1.setRoleName(role.toString());
//                role1.setEmployeeList(List.of(employeeEntity));
//                employeeRoles.add(role1);
//            });
//            employeeEntity.setRoles(employeeRoles);
//            // setting employee department
//            Department department = new Department();
//            department.setDepartmentName(employee.getDepartment());
//            department.setEmployeesInDepartment(List.of(employeeEntity));
//            //department.setProjectsInDepartment(List.of(employee.getProjects().g));
//            employeeEntity.setDepartment(department);
//            // setting employee project
//            List<Project> employeeProjects = new ArrayList<>();
//            for(Project name:employeeEntity.getProjects()){
//                Project project = new Project();
//                project.setProjectName(name.getProjectName());
//                project.setEmployees(List.of(employeeEntity));
//                project.setDepartment(department);
//                employeeProjects.add(project);
//            }
//            employeeEntity.setProjects(employeeProjects);
//            employeeRepository.save(employeeEntity);
//            response = "Employee saved successfully";
//        }
//        else{
//            response = "Employee is null";
//        }
//        return response;
//    }

//    // to authenticate employee
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }

    public String saveEmployee(Employee employee){
        employeeRepository.save(employee);
        return "Employee saved successfully";
    }

    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    public String updateEmployee(Long id, @Valid Employee updatedEmployee) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employee.get().setName(updatedEmployee.getName());
            employee.get().setDepartment(updatedEmployee.getDepartment());
            employee.get().setRoles(updatedEmployee.getRoles());
            employee.get().setEmail(updatedEmployee.getEmail());
            employee.get().setPassword(updatedEmployee.getPassword());
            employee.get().setDepartment(updatedEmployee.getDepartment());
            employee.get().setProjects(updatedEmployee.getProjects());
            employeeRepository.save(employee.get());
        }
        else{
            return "Employee not found";
        }
        return "Employee updated successfully";
    }

    public String deleteEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isPresent()){
            employeeRepository.delete(employee.get());
        }
        else{
            return "Employee not found";
        }
        return "Employee deleted successfully";
    }

    public List<Employee> search(String firstName, String email, Long departmentId) {
        return employeeRepository.findAll((root,query,cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(firstName!=null){
                predicates.add(cb.like(root.get("name"),"%" + firstName + "%"));
            }
            // Search by email
            if (email != null) {
                predicates.add(cb.equal(root.get("email"), email));
            }

            // Search by department ID
            if (departmentId != null) {
                predicates.add(cb.equal(root.get("department").get("id"), departmentId));
            }

            // Combine all predicates with 'AND' logic
            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    public List<Employee> fetchUnassignedEmployeesToProjects() {
        List<Employee> allEmloyees = employeeRepository.findAll();
        List<Employee> unassignedEmployees = new ArrayList<>();
        for(Employee employee : allEmloyees){
            if(employee.getProjects().isEmpty()){
                unassignedEmployees.add(employee);
            }
        }
        return unassignedEmployees;
    }

    public List<Employee> getEmployeeProjects(Long projectId) {
        return employeeRepository.findByProjects_Id(projectId);
    }
}
