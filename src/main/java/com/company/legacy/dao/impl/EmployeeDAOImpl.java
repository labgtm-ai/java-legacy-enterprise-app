package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
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


    private Vector<Employee> employees;


    public EmployeeDAOImpl() {

        employees = FakeDatabase.getEmployees();

    }


    @Override
    public List<Employee> findAll() {
        return CompletableFuture.supplyAsync(() -> {
            return employees.stream().collect(Collectors.toList());
        }).join();
    }


    @Override
    public Employee findById(Integer id) { // Changed return type to Employee to match interface
        return CompletableFuture.supplyAsync(() -> {
            if (id == null) {
                return null; // Return null for null id, as per interface contract
            }

            // Using Java Stream API to find the employee
            return employees.stream()
                            .filter(employee -> employee != null && employee.getId() != null && employee.getId().equals(id))
                            .findFirst()
                            .orElse(null); // Unwrap Optional, return null if not found
        }).join();
    }


    @Override
    public Employee save(Employee employee) { // Changed return type to Employee to match interface
        return CompletableFuture.supplyAsync(() -> {
            if (employee == null) {
                return null; // Return null for null employee input
            }

            if (employee.getId() == null) {
                int nextId = employees.size() + 1001;
                employee.setId(nextId);
            }
            employees.add(employee);
            return employee; // Return the saved employee directly
        }).join();
    }



    @Override
    public Employee update(Employee employee) { // Changed return type to Employee to match interface
        return CompletableFuture.supplyAsync(() -> {
            if (employee == null || employee.getId() == null) {
                return null; // Return null for invalid employee input
            }

            for (int i = 0;
                 i < employees.size();
                 i++) {


                Employee existing =
                        employees.get(i);


                if (existing != null
                        && existing.getId()
                        .equals(employee.getId())) {


                    employees.set(i, employee);


                    return employee; // Return the updated employee directly

                }

            }


            return null; // If no employee was found and updated, return null
        }).join();
    }



    @Override
    public void delete(Integer id) {
        CompletableFuture.runAsync(() -> {
            if (id == null) {

                return;

            }


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
        }).join();
    }



    @Override
    public List<Employee> searchByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            List<Employee> result =
                    new ArrayList<Employee>();


            if (name == null) {

                return result;

            }


            for (int i = 0;
                 i < employees.size();
                 i++) {


                Employee employee =
                        employees.get(i);


                if (employee != null) {


                    String fullName =
                            employee.getFirstName()
                                    + " "
                                    + employee.getLastName();


                    if (fullName
                            .toLowerCase()
                            .contains(name.toLowerCase())) {


                        result.add(employee);

                    }

                }

            }


            return result;
        }).join();
    }



    @Override
    public List<Employee> findByDepartment(
            Integer departmentId) {
        return CompletableFuture.supplyAsync(() -> {
            List<Employee> result =
                    new ArrayList<Employee>();


            if (departmentId == null) {

                return result;

            }



            Iterator<Employee> iterator =
                    employees.iterator();



            while (iterator.hasNext()) {


                Employee employee =
                        iterator.next();


                if (employee != null
                        && employee.getDepartment() != null
                        && employee.getDepartment()
                        .getId()
                        .equals(departmentId)) {


                    result.add(employee);

                }

            }


            return result;
        }).join();
    }



    @Override
    public int count() { // Changed return type from Integer to int
        return CompletableFuture.supplyAsync(() -> {
            int count = 0;


            for (int i = 0;
                 i < employees.size();
                 i++) {


                if (employees.get(i) != null) {

                    count++;

                }

            }


            return count;
        }).join();
    }



    /**
     * Legacy sorting implementation.
     *
     * Uses anonymous Comparator instead of lambda.
     */
    public CompletableFuture<List<Employee>> sortByName() {
        return CompletableFuture.supplyAsync(() -> {
            List<Employee> employeeList =
                    findAll(); // Now calls the synchronous findAll



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
        });
    }



    /**
     * Legacy report generation.
     */
    public CompletableFuture<String> generateEmployeeSummary() {
        return CompletableFuture.supplyAsync(() -> {
            StringBuilder buffer =
                    new StringBuilder();


            for (int i = 0;
                 i < employees.size();
                 i++) {


                Employee employee =
                        employees.get(i);


                if (employee != null) {


                    buffer.append(
                            employee.getId());


                    buffer.append(" - ");


                    buffer.append(
                            employee.getFirstName());


                    buffer.append("\n");

                }

            }


            return buffer.toString();
        });
    }


}