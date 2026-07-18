package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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


    private List<Employee> employees; // SRAO: Replaced Vector with List for modern collection usage.


    public EmployeeDAOImpl() {

        // SRAO: Converted the Vector returned by FakeDatabase to an ArrayList.
        employees = new ArrayList<>(FakeDatabase.getEmployees());

    }


    @Override
    public synchronized List<Employee> findAll() {
        // SRAO: Replaced manual iteration with stream for conciseness and robustness against nulls.
        return employees.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public synchronized Employee findById(Integer id) {
        // SRAO: Replaced explicit null check and loop with Optional and streams.
        return Optional.ofNullable(id)
                .flatMap(searchId -> employees.stream()
                        .filter(Objects::nonNull)
                        .filter(employee -> employee.getId() != null && employee.getId().equals(searchId))
                        .findFirst())
                .orElse(null);
    }


    @Override
    public synchronized Employee save(Employee employee) {
        // SRAO: Replaced explicit null check for 'employee' parameter with Optional.
        return Optional.ofNullable(employee)
                .map(e -> {
                    if (e.getId() == null) {
                        int nextId = employees.size() + 1001;
                        e.setId(nextId);
                    }
                    employees.add(e);
                    return e;
                })
                .orElse(null);
    }



    @Override
    public synchronized Employee update(Employee employee) {
        // SRAO: Replaced explicit null checks for 'employee' and 'employee.getId()' with Optional and streams for finding.
        return Optional.ofNullable(employee)
                .filter(e -> e.getId() != null)
                .flatMap(e -> {
                    for (int i = 0; i < employees.size(); i++) {
                        Employee existing = employees.get(i);
                        if (existing != null && existing.getId() != null && existing.getId().equals(e.getId())) {
                            employees.set(i, e);
                            return Optional.of(e);
                        }
                    }
                    return Optional.empty();
                })
                .orElse(null);
    }



    @Override
    public synchronized void delete(Integer id) {
        // SRAO: Replaced explicit null check for 'id' parameter with Optional and used removeIf.
        Optional.ofNullable(id)
                .ifPresent(searchId -> {
                    employees.removeIf(employee -> employee != null && employee.getId() != null && employee.getId().equals(searchId));
                });
    }



    @Override
    public synchronized List<Employee> searchByName(String name) {
        // SRAO: Replaced explicit null check for 'name' parameter and loop with Optional and streams.
        return Optional.ofNullable(name)
                .map(searchName -> employees.stream()
                        .filter(Objects::nonNull)
                        .filter(employee -> {
                            String fullName = employee.getFirstName() + " " + employee.getLastName();
                            return fullName.toLowerCase().contains(searchName.toLowerCase());
                        })
                        .collect(Collectors.toCollection(ArrayList::new)))
                .orElse(new ArrayList<>());
    }



    @Override
    public synchronized List<Employee> findByDepartment(Integer departmentId) {
        // SRAO: Replaced explicit null check for 'departmentId' parameter and loop with Optional and streams.
        return Optional.ofNullable(departmentId)
                .map(searchDepartmentId -> employees.stream()
                        .filter(Objects::nonNull)
                        .filter(employee -> employee.getDepartment() != null && employee.getDepartment().getId() != null && employee.getDepartment().getId().equals(searchDepartmentId))
                        .collect(Collectors.toCollection(ArrayList::new)))
                .orElse(new ArrayList<>());
    }



    @Override
    public int count() {
        // SRAO: Replaced manual loop with stream for counting non-null employees.
        return (int) employees.stream()
                .filter(Objects::nonNull)
                .count();
    }



    /**
     * Legacy sorting implementation.
     *
     * Uses anonymous Comparator instead of lambda.
     */
    public List<Employee> sortByName() {


        List<Employee> employeeList =
                findAll();



        Collections.sort(
                employeeList,
                new Comparator<Employee>() {


                    @Override
                    public int compare(
                            Employee e1,
                            Employee e2) {


                        return e1.getFirstName()
                                .compareTo(
                                        e2.getFirstName());

                    }

                });



        return employeeList;

    }



    /**
     * Legacy report generation.
     */
    public String generateEmployeeSummary() {
        // SRAO: Replaced StringBuffer and manual loop with StringBuilder and streams, handling null employees.
        return employees.stream()
                .filter(Objects::nonNull)
                .map(employee -> employee.getId() + " - " + employee.getFirstName())
                .collect(Collectors.joining("\n"));
    }


}