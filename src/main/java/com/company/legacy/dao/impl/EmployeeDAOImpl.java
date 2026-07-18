package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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

        employees = new ArrayList<>(FakeDatabase.getEmployees()); // SRAO: Converted the initial Vector from FakeDatabase to an ArrayList.

    }


    @Override
    public synchronized List<Employee> findAll() {

        // SRAO: Replaced for-loop with Stream API for collecting all employees.
        return employees.stream().collect(Collectors.toList());

    }


    @Override
    public synchronized Employee findById(Integer id) {


        if (id == null) {

            return null;

        }

        // SRAO: Replaced Iterator loop with Stream API for finding an employee by ID.
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

        // SRAO: Replaced for-loop with Stream API to find the index for updating an employee.
        int index = IntStream.range(0, employees.size())
                .filter(i -> employees.get(i) != null
                        && employees.get(i).getId() != null
                        && employees.get(i).getId().equals(employee.getId()))
                .findFirst()
                .orElse(-1);

        if (index != -1) {
            employees.set(index, employee);
            return employee;
        }


        return null;

    }



    @Override
    public synchronized void delete(Integer id) {


        if (id == null) {

            return;

        }

        // SRAO: Replaced Iterator loop with List.removeIf for deleting an employee by ID.
        employees.removeIf(employee -> employee != null
                && employee.getId() != null
                && employee.getId().equals(id));


    }



    @Override
    public synchronized List<Employee> searchByName(String name) {


        if (name == null) {

            return new ArrayList<>();

        }

        // SRAO: Replaced for-loop with Stream API for searching employees by name.
        return employees.stream()
                .filter(employee -> employee != null
                        && (employee.getFirstName() + " " + employee.getLastName())
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());

    }



    @Override
    public synchronized List<Employee> findByDepartment(
            Integer departmentId) {


        if (departmentId == null) {

            return new ArrayList<>();

        }

        // SRAO: Replaced Iterator loop with Stream API for finding employees by department.
        return employees.stream()
                .filter(employee -> employee != null
                        && employee.getDepartment() != null
                        && employee.getDepartment().getId() != null
                        && employee.getDepartment().getId().equals(departmentId))
                .collect(Collectors.toList());

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