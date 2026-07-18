package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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


        // SRAO: Replaced traditional for-loop with Stream API's toList().
        return employees.stream().toList();

    }


    @Override
    public synchronized Employee findById(Integer id) {


        if (id == null) {

            return null;

        }


        // SRAO: Replaced traditional while-loop with Iterator with Stream API's filter and findFirst.
        return employees.stream()
                .filter(employee -> employee != null && employee.getId() != null && employee.getId().equals(id))
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


        // SRAO: Replaced traditional for-loop with IntStream to find and update the employee in place.
        return IntStream.range(0, employees.size())
                .filter(i -> {
                    Employee existing = employees.get(i);
                    return existing != null && existing.getId() != null && existing.getId().equals(employee.getId());
                })
                .mapToObj(i -> {
                    employees.set(i, employee); // Side effect: update in place
                    return employee;
                })
                .findFirst()
                .orElse(null);

    }



    @Override
    public synchronized void delete(Integer id) {


        if (id == null) {

            return;

        }


        // SRAO: Replaced traditional while-loop with Iterator with Collection.removeIf().
        employees.removeIf(employee -> employee != null && employee.getId() != null && employee.getId().equals(id));


    }



    @Override
    public synchronized List<Employee> searchByName(String name) {


        if (name == null) {

            return new ArrayList<>();

        }


        String lowerCaseName = name.toLowerCase();
        // SRAO: Replaced traditional for-loop with Stream API's filter and toList().
        return employees.stream()
                .filter(employee -> employee != null &&
                        (employee.getFirstName() + " " + employee.getLastName())
                                .toLowerCase()
                                .contains(lowerCaseName))
                .toList();

    }



    @Override
    public synchronized List<Employee> findByDepartment(
            Integer departmentId) {


        if (departmentId == null) {

            return new ArrayList<>();

        }


        // SRAO: Replaced traditional while-loop with Iterator with Stream API's filter and toList().
        return employees.stream()
                .filter(employee -> employee != null &&
                        employee.getDepartment() != null &&
                        employee.getDepartment().getId() != null &&
                        employee.getDepartment().getId().equals(departmentId))
                .toList();

    }



    @Override
    public int count() {


        // SRAO: Replaced traditional for-loop with Stream API's filter and count().
        return (int) employees.stream()
                .filter(java.util.Objects::nonNull)
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


        // SRAO: Replaced traditional for-loop with Stream API's filter, map, and Collectors.joining().
        return employees.stream()
                .filter(java.util.Objects::nonNull)
                .map(employee -> employee.getId() + " - " + employee.getFirstName())
                .collect(java.util.stream.Collectors.joining("\n"));

    }


}