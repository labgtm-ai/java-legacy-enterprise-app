package com.company.legacy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.company.legacy.dao.EmployeeDAO;
import com.company.legacy.dto.DepartmentResponse;
import com.company.legacy.dto.EmployeeRequest;
import com.company.legacy.dto.EmployeeResponse;
import com.company.legacy.entity.Employee;
import com.company.legacy.exception.ResourceNotFoundException;
import com.company.legacy.service.EmployeeService;

/**
 * Legacy implementation of Employee Service.
 *
 * Contains business logic and manual mapping.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO; // SRAO: Changed to constructor injection and made final

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeDAO.findAll();
        // SRAO: Replaced traditional for-loop with Stream API for mapping
        return employees.stream()
                .map(this::convertToResponse)
                .toList(); // Java 16+
    }

    @Override
    public EmployeeResponse getEmployeeById(Integer id) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow
        Employee employee = Optional.ofNullable(employeeDAO.findById(id))
                                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
        return convertToResponse(employee);
    }

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = convertToEntity(request);
        employee.setStatus("ACTIVE");
        employee.setJoiningDate(new Date());
        employee.setLastModifiedDate(new Date());
        Employee savedEmployee = employeeDAO.save(employee);
        return convertToResponse(savedEmployee);
    }

    @Override
    public EmployeeResponse updateEmployee(Integer id, EmployeeRequest request) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow
        Employee existing = Optional.ofNullable(employeeDAO.findById(id))
                                    .orElseThrow(() -> new ResourceNotFoundException("Employee not found : " + id));
        existing.setFirstName(request.getFirstName());
        existing.setLastName(request.getLastName());
        existing.setEmail(request.getEmail());
        existing.setPhoneNumber(request.getPhoneNumber());
        existing.setDesignation(request.getDesignation());
        existing.setSalary(request.getSalary());
        existing.setLastModifiedDate(new Date());
        Employee updated = employeeDAO.update(existing);
        return convertToResponse(updated);
    }

    @Override
    public void deleteEmployee(Integer id) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow
        Optional.ofNullable(employeeDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : " + id));
        employeeDAO.delete(id);
    }

    @Override
    public List<EmployeeResponse> searchEmployees(String name) {
        List<Employee> employees = employeeDAO.searchByName(name);
        // SRAO: Replaced traditional for-each loop with Stream API for mapping
        return employees.stream()
                .map(this::convertToResponse)
                .toList(); // Java 16+
    }

    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(Integer departmentId) {
        List<Employee> employees = employeeDAO.findByDepartment(departmentId);
        // SRAO: Replaced traditional for-loop with Stream API for mapping
        return employees.stream()
                .map(this::convertToResponse)
                .toList(); // Java 16+
    }

    @Override
    public String generateEmployeeReport() {
        List<Employee> employees = employeeDAO.findAll();
        // SRAO: Replaced traditional for-loop with Stream API for report generation
        String employeeDetails = employees.stream()
                .map(employee -> employee.getId() + " - " + employee.getFirstName() + " " + employee.getLastName())
                .collect(Collectors.joining("\n"));

        return "Employee Report\n" +
               "================\n" +
               employeeDetails;
    }

    @Override
    public int getEmployeeCount() {
        return employeeDAO.count();
    }

    /**
     * Convert Entity to Response DTO.
     */
    private EmployeeResponse convertToResponse(Employee employee) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse
        return Optional.ofNullable(employee)
                .map(e -> {
                    EmployeeResponse response = new EmployeeResponse();
                    response.setId(e.getId());
                    response.setEmployeeCode(e.getEmployeeCode());
                    response.setFullName(e.getFirstName() + " " + e.getLastName());
                    response.setEmail(e.getEmail());
                    response.setPhoneNumber(e.getPhoneNumber());
                    response.setDesignation(e.getDesignation());
                    response.setSalary(e.getSalary());
                    response.setJoiningDate(e.getJoiningDate());
                    response.setStatus(e.getStatus());
                    response.setManager(e.isManager());
                    response.setSkills(e.getSkills());
                    Optional.ofNullable(e.getDepartment()).ifPresent(dept -> {
                        DepartmentResponse department = new DepartmentResponse();
                        department.setId(dept.getId());
                        department.setName(dept.getName());
                        department.setLocation(dept.getLocation());
                        department.setActive(dept.getActive());
                        response.setDepartment(department);
                    });
                    Optional.ofNullable(e.getAddress()).ifPresent(address -> {
                        response.setCity(address.getCity());
                        response.setCountry(address.getCountry());
                    });
                    return response;
                })
                .orElse(null);
    }

    /**
     * Convert Request DTO to Entity.
     */
    private Employee convertToEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        employee.setAddress(request.getAddress());
        employee.setManager(request.isManager());
        employee.setSkills(request.getSkills());
        return employee;
    }
}