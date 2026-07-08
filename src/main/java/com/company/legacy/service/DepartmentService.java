package com.company.legacy.service;

import java.util.List;

import com.company.legacy.dto.DepartmentResponse;
import com.company.legacy.entity.Department;


/**
 * Service contract for Department operations.
 *
 * Traditional enterprise service layer interface.
 */
public interface DepartmentService {


    /**
     * Retrieve all departments.
     *
     * @return list of departments
     */
    List<DepartmentResponse> getAllDepartments();



    /**
     * Retrieve department by id.
     *
     * @param id department identifier
     * @return department response
     */
    DepartmentResponse getDepartmentById(
            Integer id);



    /**
     * Create a new department.
     *
     * @param department department object
     * @return created department response
     */
    DepartmentResponse createDepartment(
            Department department);



    /**
     * Update existing department.
     *
     * @param id department identifier
     * @param department updated department object
     * @return updated response
     */
    DepartmentResponse updateDepartment(
            Integer id,
            Department department);



    /**
     * Delete department.
     *
     * @param id department identifier
     */
    void deleteDepartment(
            Integer id);



    /**
     * Search department by name.
     *
     * @param name search criteria
     * @return matching departments
     */
    List<DepartmentResponse> searchDepartments(
            String name);



    /**
     * Generate department summary report.
     *
     * @return report text
     */
    String generateDepartmentReport();



    /**
     * Count departments.
     *
     * @return number of departments
     */
    int getDepartmentCount();

}