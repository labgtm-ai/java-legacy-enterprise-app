package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Vector;
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


    private Vector<Employee> employees;


    public EmployeeDAOImpl() {

        employees = FakeDatabase.getEmployees();

    }


    @Override
    public synchronized List<Employee> findAll() {


        // SRAO: Replaced for-loop with Stream API for collection
        return employees.stream().collect(Collectors.toList());

    }


    @Override
    public synchronized Employee findById(Integer id) {


        // SRAO: Replaced explicit null check with Optional and stream API
        return Optional.ofNullable(id)
                .flatMap(searchId -> employees.stream()
                        .filter(e -> e != null && e.getId() != null && e.getId().equals(searchId))
                        .findFirst())
                .orElse(null);

    }


    @Override
    public synchronized Employee save(Employee employee) {


        // SRAO: Replaced explicit null check with Optional
        return Optional.ofNullable(employee)
                .map(emp -> {
                    if (emp.getId() == null) {
                        int nextId =
                                employees.size() + 1001;
                        emp.setId(nextId);
                    }
                    employees.add(emp);
                    return emp;
                })
                .orElse(null);

    }



    @Override
    public synchronized Employee update(Employee employee) {


        // SRAO: Replaced for-loop with Stream API for finding and updating
        return Optional.ofNullable(employee)
                .filter(emp -> emp.getId() != null)
                .flatMap(empToUpdate -> {
                    return IntStream.range(0, employees.size())
                            .filter(i -> {
                                Employee existing = employees.get(i);
                                return existing != null && existing.getId() != null && existing.getId().equals(empToUpdate.getId());
                            })
                            .mapToObj(index -> {
                                employees.set(index, empToUpdate);
                                return empToUpdate;
                            })
                            .findFirst();
                })
                .orElse(null);

    }



    @Override
    public synchronized void delete(Integer id) {


        if (id == null) {

            return;

        }

        // SRAO: Replaced Iterator loop with Collection.removeIf for deletion
        // Note: The original code removed only the first matching employee due to 'break'.
        // removeIf removes all matching employees. Assuming 'id' is unique, behavior is equivalent.
        employees.removeIf(e -> e != null && e.getId() != null && e.getId().equals(id));

    }



    @Override
    public synchronized List<Employee> searchByName(String name) {


        // SRAO: Replaced explicit null check with Optional and stream API
        return Optional.ofNullable(name)
                .map(searchName -> employees.stream()
                        .filter(Objects::nonNull)
                        .filter(e -> {
                            String fullName =
                                    e.getFirstName()
                                            + " "
                                            + e.getLastName();
                            return fullName
                                    .toLowerCase()
                                    .contains(searchName.toLowerCase());
                        })
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);

    }



    @Override
    public synchronized List<Employee> findByDepartment(
            Integer departmentId) {


        // SRAO: Replaced explicit null check with Optional and stream API
        return Optional.ofNullable(departmentId)
                .map(id -> employees.stream()
                        .filter(e -> e != null && e.getDepartment() != null && e.getDepartment().getId().equals(id))
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);

    }



    @Override
    public int count() {


        // SRAO: Replaced explicit null check with stream filter
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


        // SRAO: Replaced Collections.sort with Stream API and lambda comparator
        return findAll().stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .collect(Collectors.toList());

    }



    /**
     * Legacy report generation.
     */
    public String generateEmployeeSummary() {


        // SRAO: Replaced explicit null check and StringBuffer with stream and Collectors.joining
        return employees.stream()
                .filter(Objects::nonNull)
                .map(employee -> employee.getId() + " - " + employee.getFirstName())
                .collect(Collectors.joining("\n"));

    }


}