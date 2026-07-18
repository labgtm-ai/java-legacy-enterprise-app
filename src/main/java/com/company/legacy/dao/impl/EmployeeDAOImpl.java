package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors; // SRAO: Added for Stream API operations
import java.util.Objects; // SRAO: Added for Objects::nonNull

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
        // SRAO: Replaced traditional for-loop with Stream API for collection transformation
        return employees.stream().collect(Collectors.toList());
    }


    @Override
    public synchronized Employee findById(Integer id) {


        if (id == null) {

            return null;

        }


        // SRAO: Replaced traditional while-loop with Iterator with Stream API for finding an element
        return employees.stream()
                .filter(employee -> employee != null
                        && employee.getId() != null
                        && employee.getId().equals(id))
                .findFirst()
                .orElse(null);

    }


    @Override
    public synchronized Employee save(Employee employee) {


        if (employee == null) {

            return null;

        }


        if (employee.getId() == null) {


            int nextId =
                    employees.size() + 1001;


            employee.setId(nextId);

        }


        employees.add(employee);


        return employee;

    }



    @Override
    public synchronized Employee update(Employee employee) {


        if (employee == null
                || employee.getId() == null) {

            return null;

        }


        // SRAO: Kept traditional for-loop as Stream API is not suitable for in-place modification of Vector by index
        for (int i = 0;
             i < employees.size();
             i++) {


            Employee existing =
                    employees.get(i);


            if (existing != null
                    && existing.getId()
                    .equals(employee.getId())) {


                employees.set(i, employee);


                return employee;

            }

        }


        return null;

    }



    @Override
    public synchronized void delete(Integer id) {


        if (id == null) {

            return;

        }


        // SRAO: Kept traditional while-loop with Iterator as Stream API is not suitable for in-place removal using Iterator.remove()
        Iterator<Employee> iterator =
                employees.iterator();


        while (iterator.hasNext()) {


            Employee employee =
                    iterator.next();


            if (employee != null
                    && employee.getId()
                    .equals(id)) {


                iterator.remove();

                break;

            }

        }


    }



    @Override
    public synchronized List<Employee> searchByName(String name) {


        if (name == null) {

            return new ArrayList<>(); // SRAO: Replaced traditional for-loop with Stream API for filtering

        }


        final String lowerCaseName = name.toLowerCase();

        return employees.stream()
                .filter(employee -> employee != null
                        && employee.getFirstName() != null
                        && employee.getLastName() != null
                        && (employee.getFirstName() + " " + employee.getLastName()).toLowerCase().contains(lowerCaseName))
                .collect(Collectors.toList());

    }



    @Override
    public synchronized List<Employee> findByDepartment(
            Integer departmentId) {


        if (departmentId == null) {

            return new ArrayList<>(); // SRAO: Replaced traditional while-loop with Iterator with Stream API for filtering

        }


        return employees.stream()
                .filter(employee -> employee != null
                        && employee.getDepartment() != null
                        && employee.getDepartment().getId() != null
                        && employee.getDepartment().getId().equals(departmentId))
                .collect(Collectors.toList());

    }



    @Override
    public synchronized int count() {


        // SRAO: Replaced traditional for-loop with Stream API for counting elements
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


        // SRAO: Replaced Collections.sort with anonymous Comparator with Stream API and lambda Comparator
        return findAll().stream()
                .sorted(Comparator.comparing(Employee::getFirstName))
                .collect(Collectors.toList());

    }



    /**
     * Legacy report generation.
     */
    public String generateEmployeeSummary() {


        // SRAO: Replaced traditional for-loop with StringBuffer with Stream API for string aggregation
        return employees.stream()
                .filter(Objects::nonNull)
                .map(employee -> employee.getId() + " - " + employee.getFirstName())
                .collect(Collectors.joining("\n"));

    }


}