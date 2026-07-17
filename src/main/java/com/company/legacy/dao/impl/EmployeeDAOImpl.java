package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
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

        // SRAO: Replaced traditional for-loop with Stream API to collect all employees.
        return employees.stream().collect(Collectors.toList());

    }


    @Override
    public synchronized Employee findById(Integer id) {


        // SRAO: Replaced explicit null check with Optional for 'id' and stream for employee search.
        return Optional.ofNullable(id)
                .flatMap(searchId -> employees.stream()
                        .filter(e -> e != null && e.getId() != null && e.getId().equals(searchId))
                        .findFirst())
                .orElse(null);

    }


    @Override
    public synchronized Employee save(Employee employee) {


        // SRAO: Replaced explicit null check with Optional for 'employee'.
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


        // SRAO: Replaced explicit null check with Optional for 'employee' and its ID.
        return Optional.ofNullable(employee)
                .filter(emp -> emp.getId() != null)
                .map(empToUpdate -> {
                    // SRAO: Replaced traditional for-loop with IntStream to find and update employee by ID.
                    return IntStream.range(0, employees.size())
                            .filter(i -> employees.get(i) != null && employees.get(i).getId() != null && employees.get(i).getId().equals(empToUpdate.getId()))
                            .mapToObj(i -> {
                                employees.set(i, empToUpdate);
                                return empToUpdate;
                            })
                            .findFirst()
                            .orElse(null);
                })
                .orElse(null);

    }



    @Override
    public synchronized void delete(Integer id) {


        // SRAO: Replaced explicit null check with Optional for 'id' and used removeIf for deletion.
        Optional.ofNullable(id)
                .ifPresent(idToDelete -> employees.removeIf(e -> e != null && e.getId() != null && e.getId().equals(idToDelete)));

    }



    @Override
    public synchronized List<Employee> searchByName(String name) {


        // SRAO: Replaced explicit null check with Optional for 'name' and used streams for filtering.
        return Optional.ofNullable(name)
                .map(searchName -> employees.stream()
                        .filter(e -> e != null && e.getFirstName() != null && e.getLastName() != null)
                        .filter(e -> (e.getFirstName() + " " + e.getLastName()).toLowerCase().contains(searchName.toLowerCase()))
                        .collect(java.util.stream.Collectors.toList()))
                .orElse(new ArrayList<>());

    }



    @Override
    public synchronized List<Employee> findByDepartment(
            Integer departmentId) {


        // SRAO: Replaced explicit null check with Optional for 'departmentId' and used streams for filtering.
        return Optional.ofNullable(departmentId)
                .map(searchDeptId -> employees.stream()
                        .filter(e -> e != null && e.getDepartment() != null && e.getDepartment().getId() != null && e.getDepartment().getId().equals(searchDeptId))
                        .collect(java.util.stream.Collectors.toList()))
                .orElse(new ArrayList<>());

    }



    @Override
    public int count() {


        // SRAO: Replaced explicit null check with stream filter for non-null elements.
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


        // SRAO: Replaced explicit null check with stream filter and map for summary generation.
        return employees.stream()
                .filter(java.util.Objects::nonNull)
                .map(employee -> employee.getId() + " - " + employee.getFirstName() + "\n")
                .collect(java.util.stream.Collectors.joining());

    }


}