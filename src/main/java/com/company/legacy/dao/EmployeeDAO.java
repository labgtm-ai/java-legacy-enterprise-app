package com.company.legacy.dao;

import java.util.List;

import com.company.legacy.entity.Employee;


/**
 * Data Access Object interface for Employee operations.
 *
 * Legacy enterprise applications commonly separated
 * DAO contracts from implementations.
 */
public interface EmployeeDAO {


    /**
     * Retrieve all employees.
     *
     * @return list of employees
     */
    List<Employee> findAll();


    /**
     * Find employee by identifier.
     *
     * @param id employee id
     * @return employee object
     */
    Employee findById(Integer id);


    /**
     * Save new employee.
     *
     * @param employee employee object
     * @return saved employee
     */
    Employee save(Employee employee);


    /**
     * Update existing employee.
     *
     * @param employee employee object
     * @return updated employee
     */
    Employee update(Employee employee);


    /**
     * Delete employee.
     *
     * @param id employee id
     */
    void delete(Integer id);


    /**
     * Search employees by name.
     *
     * @param name employee name
     * @return matching employees
     */
    List<Employee> searchByName(String name);


    /**
     * Find employees belonging to department.
     *
     * @param departmentId department identifier
     * @return employees
     */
    List<Employee> findByDepartment(Integer departmentId);


    /**
     * Count employees.
     *
     * @return employee count
     */
    int count();

}