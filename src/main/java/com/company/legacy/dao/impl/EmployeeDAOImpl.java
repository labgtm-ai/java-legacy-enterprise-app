package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Repository;

import com.company.legacy.dao.EmployeeDAO;
import com.company.legacy.entity.Employee;
import com.company.legacy.repository.FakeDatabase;


/**
 * Legacy implementation of Employee DAO.
 *
 * This class intentionally contains older Java coding patterns
 * to simulate a real enterprise legacy application.
 */
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {


    private List<Employee> employees; // SRAO: Replaced Vector with List for better performance and modern API usage.


    public EmployeeDAOImpl() {

        // SRAO: Modernized to safely convert a potentially raw or untyped collection from FakeDatabase to a List<Employee> without unchecked warnings.
        employees = FakeDatabase.getEmployees().stream()
                .filter(Employee.class::isInstance)
                .map(Employee.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));

    }


    @Override
    public synchronized List<Employee> findAll() {

        // SRAO: Replaced for-loop with Stream API for collecting all employees.
        return employees.stream().collect(Collectors.toList());

    }


    @Override
    public synchronized Employee findById(Integer id) {
        // SRAO: Replaced explicit null check with Optional for the 'id' parameter and employee properties.
        return Optional.ofNullable(id)
                .flatMap(validId -> employees.stream()
                        .filter(Objects::nonNull) // Filter out null employees
                        .filter(employee -> Optional.ofNullable(employee.getId())
                                .filter(employeeId -> employeeId.equals(validId))
                                .isPresent())
                        .findFirst())
                .orElse(null);
    }


    @Override
    public synchronized Employee save(Employee employee) {
        // SRAO: Replaced explicit null check with Optional for the 'employee' parameter.
        return Optional.ofNullable(employee)
                .map(emp -> {
                    if (emp.getId() == null) {
                        int nextId = employees.size() + 1001;
                        emp.setId(nextId);
                    }
                    employees.add(emp);
                    return emp;
                })
                .orElse(null);
    }



    @Override
    public synchronized Employee update(Employee employee) {
        // SRAO: Replaced explicit null check with Optional for the 'employee' parameter and its ID.
        return Optional.ofNullable(employee)
                .filter(emp -> emp.getId() != null) // Ensure employee and its ID are not null
                .flatMap(emp -> {
                    int index = IntStream.range(0, employees.size())
                            .filter(i -> {
                                Employee currentEmployee = employees.get(i);
                                return Optional.ofNullable(currentEmployee)
                                        .map(Employee::getId)
                                        .filter(id -> id.equals(emp.getId()))
                                        .isPresent();
                            })
                            .findFirst()
                            .orElse(-1);

                    if (index != -1) {
                        employees.set(index, emp);
                        return Optional.of(emp);
                    }
                    return Optional.empty();
                })
                .orElse(null);
    }



    @Override
    public synchronized void delete(Integer id) {
        // SRAO: Replaced explicit null check with Optional for the 'id' parameter.
        Optional.ofNullable(id)
                .ifPresent(validId -> employees.removeIf(employee ->
                        Optional.ofNullable(employee)
                                .map(Employee::getId)
                                .filter(employeeId -> employeeId.equals(validId))
                                .isPresent()));
    }



    @Override
    public synchronized List<Employee> searchByName(String name) {
        // SRAO: Replaced explicit null check with Optional for the 'name' parameter.
        return Optional.ofNullable(name)
                .map(validName -> employees.stream()
                        .filter(Objects::nonNull) // Filter out null employees
                        .filter(employee -> (employee.getFirstName() + " " + employee.getLastName())
                                .toLowerCase()
                                .contains(validName.toLowerCase()))
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }



    @Override
    public synchronized List<Employee> findByDepartment(Integer departmentId) {
        // SRAO: Replaced explicit null check with Optional for the 'departmentId' parameter and nested properties.
        return Optional.ofNullable(departmentId)
                .map(validDepartmentId -> employees.stream()
                        .filter(Objects::nonNull) // Filter out null employees
                        .filter(employee ->
                                Optional.ofNullable(employee.getDepartment())
                                        .map(dept -> dept.getId())
                                        .filter(id -> id.equals(validDepartmentId))
                                        .isPresent())
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }



    @Override
    public int count() {

        // SRAO: Replaced for-loop with Stream API for counting non-null employees.
        return (int) employees.stream().filter(Objects::nonNull).count();

    }



    /**
     * Legacy sorting implementation.
     *
     * Uses anonymous Comparator instead of lambda.
     */
    public List<Employee> sortByName() {


        List<Employee> employeeList =
                findAll();

        // SRAO: Replaced anonymous Comparator with lambda expression for sorting.
        employeeList.sort(Comparator.comparing(Employee::getFirstName));


        return employeeList;

    }



    /**
     * Legacy report generation.
     */
    public String generateEmployeeSummary() {

        // SRAO: Replaced for-loop and StringBuffer with Stream API for summary generation.
        return employees.stream()
                .filter(Objects::nonNull)
                .map(employee -> employee.getId() + " - " + employee.getFirstName())
                .collect(Collectors.joining("\n"));

    }


}