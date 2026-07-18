package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

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


        List<Employee> employeeList =
                new ArrayList<Employee>();


        for (int i = 0; i < employees.size(); i++) {

            Employee employee =
                    employees.get(i);

            employeeList.add(employee);

        }


        return employeeList;

    }


    @Override
    public synchronized Employee findById(Integer id) {


        Employee result = null;


        if (id == null) {

            return null;

        }


        Iterator<Employee> iterator =
                employees.iterator();


        while (iterator.hasNext()) {


            Employee employee =
                    iterator.next();


            if (employee != null
                    && employee.getId() != null
                    && employee.getId().equals(id)) {


                result = employee;

                break;

            }

        }


        return result;

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

    }



    @Override
    public synchronized List<Employee> findByDepartment(
            Integer departmentId) {


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

    }



    @Override
    public int count() {


        int count = 0;


        for (int i = 0;
             i < employees.size();
             i++) {


            if (employees.get(i) != null) {

                count++;

            }

        }


        return count;

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


        StringBuffer buffer =
                new StringBuffer();


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

    }


}