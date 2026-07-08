package com.company.legacy.service;

import java.util.List;

import com.company.legacy.dto.EmployeeRequest;
import com.company.legacy.dto.EmployeeResponse;


/**
 * Service contract for Employee operations.
 *
 * Traditional enterprise service layer pattern.
 */
public interface EmployeeService {


    /**
     * Retrieve all employees.
     *
     * @return employee responses
     */
    List<EmployeeResponse> getAllEmployees();



    /**
     * Retrieve employee by id.
     *
     * @param id employee identifier
     * @return employee response
     */
    EmployeeResponse getEmployeeById(Integer id);



    /**
     * Create employee.
     *
     * @param request employee request
     * @return created employee
     */
    EmployeeResponse createEmployee(
            EmployeeRequest request);



    /**
     * Update employee.
     *
     * @param id employee identifier
     * @param request employee request
     * @return updated employee
     */
    EmployeeResponse updateEmployee(
            Integer id,
            EmployeeRequest request);



    /**
     * Delete employee.
     *
     * @param id employee identifier
     */
    void deleteEmployee(Integer id);



    /**
     * Search employees by name.
     *
     * @param name search text
     * @return employees
     */
    List<EmployeeResponse> searchEmployees(
            String name);



    /**
     * Find employees by department.
     *
     * @param departmentId department identifier
     * @return employees
     */
    List<EmployeeResponse> getEmployeesByDepartment(
            Integer departmentId);



    /**
     * Generate employee report.
     *
     * @return report text
     */
    String generateEmployeeReport();



    /**
     * Count employees.
     *
     * @return total employees
     */
    int getEmployeeCount();

}