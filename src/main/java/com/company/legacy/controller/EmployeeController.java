package com.company.legacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.legacy.dto.EmployeeRequest;
import com.company.legacy.dto.EmployeeResponse;
import com.company.legacy.service.EmployeeService;


/**
 * REST controller for Employee operations.
 *
 * Legacy Spring Boot controller style.
 */
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {


    @Autowired
    private EmployeeService employeeService;




    /**
     * Get all employees.
     */
    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {


        List<EmployeeResponse> employees =
                employeeService.getAllEmployees();


        return new ResponseEntity<List<EmployeeResponse>>(
                employees,
                HttpStatus.OK);

    }





    /**
     * Get employee by id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(
            @PathVariable("id") Integer id) {


        EmployeeResponse employee =
                employeeService.getEmployeeById(id);



        return new ResponseEntity<EmployeeResponse>(
                employee,
                HttpStatus.OK);

    }





    /**
     * Create employee.
     */
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(
            @RequestBody EmployeeRequest request) {


        EmployeeResponse response =
                employeeService.createEmployee(request);



        return new ResponseEntity<EmployeeResponse>(
                response,
                HttpStatus.CREATED);

    }





    /**
     * Update employee.
     */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable("id") Integer id,
            @RequestBody EmployeeRequest request) {


        EmployeeResponse response =
                employeeService.updateEmployee(
                        id,
                        request);



        return new ResponseEntity<EmployeeResponse>(
                response,
                HttpStatus.OK);

    }





    /**
     * Delete employee.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(
            @PathVariable("id") Integer id) {


        employeeService.deleteEmployee(id);



        return new ResponseEntity<String>(
                "Employee deleted successfully",
                HttpStatus.OK);

    }





    /**
     * Search employees.
     *
     * Example:
     * GET /api/employees/search/John
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<List<EmployeeResponse>> searchEmployees(
            @PathVariable("name") String name) {


        List<EmployeeResponse> employees =
                employeeService.searchEmployees(name);



        return new ResponseEntity<List<EmployeeResponse>>(
                employees,
                HttpStatus.OK);

    }





    /**
     * Get employees by department.
     *
     * Example:
     * GET /api/employees/department/1
     */
    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<EmployeeResponse>> getEmployeesByDepartment(
            @PathVariable("departmentId") Integer departmentId) {


        List<EmployeeResponse> employees =
                employeeService.getEmployeesByDepartment(
                        departmentId);



        return new ResponseEntity<List<EmployeeResponse>>(
                employees,
                HttpStatus.OK);

    }





    /**
     * Generate employee report.
     */
    @GetMapping("/report")
    public ResponseEntity<String> generateReport() {


        String report =
                employeeService.generateEmployeeReport();



        return new ResponseEntity<String>(
                report,
                HttpStatus.OK);

    }





    /**
     * Get employee count.
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getCount() {


        int count =
                employeeService.getEmployeeCount();



        return new ResponseEntity<Integer>(
                count,
                HttpStatus.OK);

    }


}