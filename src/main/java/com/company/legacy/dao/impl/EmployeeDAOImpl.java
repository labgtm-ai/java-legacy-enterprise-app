package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Objects;
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


        // SRAO: Replaced traditional for-loop with Stream API for collection transformation.
        return employees.stream()
                        .collect(Collectors.toList());

    }


    @Override
    public synchronized Employee findById(Integer id) {
        // SRAO: Replaced explicit null checks with Optional and streams for cleaner handling
        return Optional.ofNullable(id)
                .flatMap(searchId -> employees.stream()
                        .filter(Objects::nonNull)
                        .filter(e -> e.getId() != null && e.getId().equals(searchId))
                        .findFirst())
                .orElse(null);
    }


    @Override
    public synchronized Employee save(Employee employee) {
        // SRAO: Replaced explicit null check with Optional for cleaner handling
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
        // SRAO: Replaced explicit null checks with Optional for cleaner handling
        return Optional.ofNullable(employee)
                .filter(e -> e.getId() != null)
                .flatMap(empToUpdate -> {
                    // SRAO: Replaced traditional for-loop with IntStream to find and update employee by index.
                    int index = IntStream.range(0, employees.size())
                                .filter(i -> {
                                    Employee existing =
                                            employees.get(i);
                                    return existing != null
                                            && existing.getId() != null
                                            && existing.getId()
                                            .equals(empToUpdate.getId());
                                })
                                .findFirst()
                                .orElse(-1);

                    if (index != -1) {
                        employees.set(index, empToUpdate);
                        return Optional.of(empToUpdate);
                    } else {
                        return Optional.empty();
                    }
                })
                .orElse(null);
    }



    @Override
    public synchronized void delete(Integer id) {
        // SRAO: Replaced explicit null check with Optional.ifPresent and stream removeIf
        Optional.ofNullable(id).ifPresent(searchId -> {
            employees.removeIf(e -> e != null && e.getId() != null && e.getId().equals(searchId));
        });
    }



    @Override
    public synchronized List<Employee> searchByName(String name) {
        // SRAO: Replaced explicit null check with Optional and streams for cleaner handling
        return Optional.ofNullable(name)
                .map(searchName -> employees.stream()
                        .filter(Objects::nonNull)
                        .filter(e -> e.getFirstName() != null && e.getLastName() != null)
                        .filter(e -> (e.getFirstName() + " " + e.getLastName()).toLowerCase().contains(searchName.toLowerCase()))
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }



    @Override
    public synchronized List<Employee> findByDepartment(
            Integer departmentId) {
        // SRAO: Replaced explicit null check with Optional and streams for cleaner handling
        return Optional.ofNullable(departmentId)
                .map(searchDeptId -> employees.stream()
                        .filter(Objects::nonNull)
                        .filter(e -> e.getDepartment() != null && e.getDepartment().getId() != null)
                        .filter(e -> e.getDepartment().getId().equals(searchDeptId))
                        .collect(Collectors.toList()))
                .orElseGet(ArrayList::new);
    }



    @Override
    public int count() {
        // SRAO: Replaced explicit null check with stream filter for cleaner handling
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
        // SRAO: Replaced StringBuffer (or implicit string concatenation) with StringBuilder for efficiency.
        StringBuilder sb = new StringBuilder();
        employees.stream()
                .filter(Objects::nonNull)
                .forEach(employee -> sb.append(employee.getId())
                                       .append(" - ")
                                       .append(employee.getFirstName())
                                       .append("\n"));
        return sb.toString();
    }


}
