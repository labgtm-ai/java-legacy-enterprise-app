package com.company.legacy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private EmployeeDAO employeeDAO;


    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees = employeeDAO.findAll();
        return employees.stream()
                        .map(this::convertToResponse)
                        .collect(Collectors.toList());
    }


    @Override
    public EmployeeResponse getEmployeeById(Integer id) {
        Employee employee = employeeDAO.findById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee not found with id : " + id);
        }
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
        Employee existing = employeeDAO.findById(id);
        if (existing == null) {
            throw new ResourceNotFoundException("Employee not found : " + id);
        }
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
        Employee employee = employeeDAO.findById(id);
        if (employee == null) {
            throw new ResourceNotFoundException("Employee does not exist : " + id);
        }
        employeeDAO.delete(id);
    }


    @Override
    public List<EmployeeResponse> searchEmployees(String name) {
        List<Employee> employees = employeeDAO.searchByName(name);
        return employees.stream()
                        .map(this::convertToResponse)
                        .collect(Collectors.toList());
    }


    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(Integer departmentId) {
        List<Employee> employees = employeeDAO.findByDepartment(departmentId);
        return employees.stream()
                        .map(this::convertToResponse)
                        .collect(Collectors.toList());
    }


    @Override
    public String generateEmployeeReport() {
        List<Employee> employees = employeeDAO.findAll();
        StringBuilder buffer = new StringBuilder();
        buffer.append("Employee Report\n");
        buffer.append("================\n");
        for(Employee employee : employees) {
            buffer.append(employee.getId());
            buffer.append(" - ");
            buffer.append(employee.getFirstName());
            buffer.append(" ");
            buffer.append(employee.getLastName());
            buffer.append("\n");
        }
        return buffer.toString();
    }


    @Override
    public int getEmployeeCount() {
        return employeeDAO.count();
    }


    /**
     * Convert Entity to Response DTO.
     */
    private EmployeeResponse convertToResponse(Employee employee) {
        if(employee == null) {
            return null;
        }
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setFullName(employee.getFirstName() + " " + employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPhoneNumber(employee.getPhoneNumber());
        response.setDesignation(employee.getDesignation());
        response.setSalary(employee.getSalary());
        response.setJoiningDate(employee.getJoiningDate());
        response.setStatus(employee.getStatus());
        response.setManager(employee.isManager());
        response.setSkills(employee.getSkills());

        if(employee.getDepartment() != null) {
            DepartmentResponse department = new DepartmentResponse();
            department.setId(employee.getDepartment().getId());
            department.setName(employee.getDepartment().getName());
            department.setLocation(employee.getDepartment().getLocation());
            department.setActive(employee.getDepartment().getActive());
            response.setDepartment(department);
        }

        if(employee.getAddress() != null) {
            response.setCity(employee.getAddress().getCity());
            response.setCountry(employee.getAddress().getCountry());
        }
        return response;
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