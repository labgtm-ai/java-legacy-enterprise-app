package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.company.legacy.dao.DepartmentDAO;
import com.company.legacy.entity.Department;
import com.company.legacy.repository.FakeDatabase;


/**
 * Legacy implementation of Department DAO.
 *
 * Uses old collection APIs and synchronization patterns
 * commonly found in Java 8 enterprise applications.
 */
@Repository
public class DepartmentDAOImpl implements DepartmentDAO {


    private Hashtable<Integer, Department> departments;


    public DepartmentDAOImpl() {

        departments =
                FakeDatabase.getDepartments();

    }



    @Override
    public synchronized List<Department> findAll() {


        // SRAO: Replaced iterator-based while loop with Stream API for collection transformation.
        return departments.values().stream().toList();

    }



    @Override
    public synchronized Department findById(Integer id) {

        // SRAO: Replaced explicit null check with Optional for method parameter and return value.
        return Optional.ofNullable(id)
                       .map(departments::get)
                       .orElse(null);

    }



    @Override
    public synchronized Department save(
            Department department) {

        // SRAO: Replaced explicit null check with Optional for method parameter.
        return Optional.ofNullable(department)
                       .map(dept -> {
                           if (dept.getId() == null) {
                               int nextId =
                                       departments.size() + 1;
                               dept.setId(nextId);
                           }
                           departments.put(
                                   dept.getId(),
                                   dept);
                           return dept;
                       })
                       .orElse(null);

    }



    @Override
    public synchronized Department update(
            Department department) {

        // SRAO: Replaced explicit null checks with Optional for method parameter and existing department.
        return Optional.ofNullable(department)
                       .filter(dept -> dept.getId() != null)
                       .flatMap(dept -> Optional.ofNullable(departments.get(dept.getId()))
                                                .map(existing -> {
                                                    departments.put(
                                                            dept.getId(),
                                                            dept);
                                                    return dept;
                                                }))
                       .orElse(null);

    }



    @Override
    public synchronized void delete(
            Integer id) {

        // SRAO: Replaced explicit null check with Optional for method parameter.
        Optional.ofNullable(id)
                .filter(departments::containsKey)
                .ifPresent(departments::remove);

    }



    @Override
    public synchronized List<Department> searchByName(
            String name) {

        // SRAO: Replaced explicit null checks with Optional for method parameter and department name, using streams.
        return Optional.ofNullable(name)
                       .map(n -> departments.values().stream()
                                            .filter(department -> Optional.ofNullable(department.getName())
                                                                           .map(deptName -> deptName.toLowerCase().contains(n.toLowerCase()))
                                                                           .orElse(false))
                                            .collect(Collectors.toList()))
                       .orElse(new ArrayList<>());

    }



    @Override
    public int count() {


        // SRAO: Replaced for-loop with direct call to Hashtable.size() for efficiency.
        return departments.size();

    }



    /**
     * Legacy sorting method.
     */
    public List<Department> sortByName() {


        // SRAO: Replaced Collections.sort with Stream API for sorting and collecting.
        return findAll().stream()
                .sorted(Comparator.comparing(Department::getName))
                .toList();

    }



    /**
     * Legacy report generation.
     */
    public String generateDepartmentSummary() {


        // SRAO: Replaced StringBuffer and iterator-based loop with Stream API and Collectors.joining.
        return departments.values().stream()
                .map(department -> department.getId() + " - " + department.getName())
                .collect(Collectors.joining("\n"));

    }


}