package com.company.legacy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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


    private final EmployeeDAO employeeDAO;

    // SRAO: Replaced field injection with constructor injection.
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    @Override
    public List<EmployeeResponse> getAllEmployees() {


        List<Employee> employees =
                employeeDAO.findAll();


        // SRAO: Replaced traditional for-loop with Stream API.
        return employees.stream()
                .map(this::convertToResponse)
                .toList();

    }



    @Override
    public EmployeeResponse getEmployeeById(Integer id) {
        // SRAO: Replaced explicit null check with Optional.
        return Optional.ofNullable(employeeDAO.findById(id))
                .map(this::convertToResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id : " + id));
    }



    @Override
    public EmployeeResponse createEmployee(
            EmployeeRequest request) {


        Employee employee =
                convertToEntity(request);



        employee.setStatus("ACTIVE");

        employee.setJoiningDate(
                new Date());

        employee.setLastModifiedDate(
                new Date());



        Employee savedEmployee =
                employeeDAO.save(employee);



        return convertToResponse(savedEmployee);

    }




    @Override
    public EmployeeResponse updateEmployee(
            Integer id,
            EmployeeRequest request) {

        // SRAO: Replaced explicit null check with Optional.
        Employee existing = Optional.ofNullable(employeeDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found : " + id));


        existing.setFirstName(
                request.getFirstName());


        existing.setLastName(
                request.getLastName());


        existing.setEmail(
                request.getEmail());


        existing.setPhoneNumber(
                request.getPhoneNumber());


        existing.setDesignation(
                request.getDesignation());


        existing.setSalary(
                request.getSalary());



        existing.setLastModifiedDate(
                new Date());



        Employee updated =
                employeeDAO.update(existing);



        return convertToResponse(updated);

    }




    @Override
    public void deleteEmployee(Integer id) {

        // SRAO: Replaced explicit null check with Optional.
        Optional.ofNullable(employeeDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist : " + id));


        employeeDAO.delete(id);

    }




    @Override
    public List<EmployeeResponse> searchEmployees(
            String name) {


        List<Employee> employees =
                employeeDAO.searchByName(name);



        // SRAO: Replaced enhanced for-loop with Stream API.
        return employees.stream()
                .map(this::convertToResponse)
                .toList();

    }




    @Override
    public List<EmployeeResponse> getEmployeesByDepartment(
            Integer departmentId) {


        List<Employee> employees =
                employeeDAO.findByDepartment(
                        departmentId);



        // SRAO: Replaced traditional for-loop with Stream API.
        return employees.stream()
                .map(this::convertToResponse)
                .toList();

    }




    @Override
    public String generateEmployeeReport() {


        List<Employee> employees =
                employeeDAO.findAll();


        // SRAO: Replaced for-loop with Stream API for report generation.
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
    private EmployeeResponse convertToResponse(
            Employee employee) {

        // SRAO: Replaced explicit null check with Optional.
        return Optional.ofNullable(employee)
                .map(emp -> {
                    EmployeeResponse response = new EmployeeResponse();

                    response.setId(emp.getId());
                    response.setEmployeeCode(emp.getEmployeeCode());
                    response.setFullName(emp.getFirstName() + " " + emp.getLastName());
                    response.setEmail(emp.getEmail());
                    response.setPhoneNumber(emp.getPhoneNumber());
                    response.setDesignation(emp.getDesignation());
                    response.setSalary(emp.getSalary());
                    response.setJoiningDate(emp.getJoiningDate());
                    response.setStatus(emp.getStatus());
                    response.setManager(emp.isManager());
                    response.setSkills(emp.getSkills());

                    // Nested null checks for department and address
                    Optional.ofNullable(emp.getDepartment()).ifPresent(dept -> {
                        DepartmentResponse department = new DepartmentResponse();
                        department.setId(dept.getId());
                        department.setName(dept.getName());
                        department.setLocation(dept.getLocation());
                        department.setActive(dept.getActive());
                        response.setDepartment(department);
                    });

                    Optional.ofNullable(emp.getAddress()).ifPresent(address -> {
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
    private Employee convertToEntity(
            EmployeeRequest request) {



        Employee employee =
                new Employee();



        employee.setEmployeeCode(
                request.getEmployeeCode());


        employee.setFirstName(
                request.getFirstName());


        employee.setLastName(
                request.getLastName());


        employee.setEmail(
                request.getEmail());


        employee.setPhoneNumber(
                request.getPhoneNumber());


        employee.setDesignation(
                request.getDesignation());


        employee.setSalary(
                request.getSalary());


        employee.setAddress(
                request.getAddress());


        employee.setManager(
                request.isManager());


        employee.setSkills(
                request.getSkills());



        return employee;

    }


}