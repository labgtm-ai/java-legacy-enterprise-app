package com.company.legacy.dao;

import java.util.List;

import com.company.legacy.entity.Department;


/**
 * Data Access Object interface for Department operations.
 *
 * This follows the traditional DAO pattern used in
 * Java 8 enterprise applications.
 */
public interface DepartmentDAO {


    /**
     * Retrieve all departments.
     *
     * @return list of departments
     */
    List<Department> findAll();


    /**
     * Find department by identifier.
     *
     * @param id department id
     * @return department object
     */
    Department findById(Integer id);


    /**
     * Save a new department.
     *
     * @param department department object
     * @return saved department
     */
    Department save(Department department);


    /**
     * Update existing department.
     *
     * @param department department object
     * @return updated department
     */
    Department update(Department department);


    /**
     * Delete department.
     *
     * @param id department identifier
     */
    void delete(Integer id);


    /**
     * Search department by name.
     *
     * @param name department name
     * @return matching departments
     */
    List<Department> searchByName(String name);


    /**
     * Count total departments.
     *
     * @return department count
     */
    int count();


}