package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
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


    // SRAO: Replaced Hashtable with ConcurrentHashMap for better concurrency.
    private ConcurrentHashMap<Integer, Department> departments;


    public DepartmentDAOImpl() {

        departments =
                new ConcurrentHashMap<>(FakeDatabase.getDepartments());

    }



    @Override
    public List<Department> findAll() {


        // SRAO: Replaced explicit null check with stream API for conciseness.
        return departments.values().stream()
                .collect(Collectors.toCollection(ArrayList::new));

    }



    @Override
    public Department findById(Integer id) {


        // SRAO: Replaced explicit null check with Optional.ofNullable.
        return Optional.ofNullable(id)
                .map(departments::get)
                .orElse(null);

    }



    @Override
    public Department save(
            Department department) {


        // SRAO: Replaced explicit null check with Optional.ofNullable.
        return Optional.ofNullable(department)
                .map(d -> {
                    if (d.getId() == null) {
                        int nextId =
                                departments.size() + 1;
                        d.setId(nextId);
                    }
                    departments.put(
                            d.getId(),
                            d);
                    return d;
                })
                .orElse(null);

    }



    @Override
    public Department update(
            Department department) {


        // SRAO: Replaced explicit null checks with Optional.ofNullable.
        return Optional.ofNullable(department)
                .filter(d -> d.getId() != null)
                .flatMap(d -> Optional.ofNullable(departments.get(d.getId()))
                        .map(existing -> {
                            departments.put(
                                    d.getId(),
                                    d);
                            return d;
                        }))
                .orElse(null);

    }



    @Override
    public void delete(
            Integer id) {


        // SRAO: Replaced explicit null check with Optional.ifPresent.
        Optional.ofNullable(id)
                .ifPresent(departments::remove);

    }



    @Override
    public List<Department> searchByName(
            String name) {


        // SRAO: Replaced explicit null checks with Optional and stream API.
        return Optional.ofNullable(name)
                .map(n -> departments.values().stream()
                        .filter(department -> Optional.ofNullable(department.getName())
                                .map(deptName -> deptName.toLowerCase().contains(n.toLowerCase()))
                                .orElse(false))
                        .collect(Collectors.toCollection(ArrayList::new)))
                .orElseGet(ArrayList::new);

    }



    @Override
    public int count() {


        // SRAO: Removed redundant null check as Hashtable does not store null values.
        return departments.size();

    }



    /**
     * Legacy sorting method.
     */
    public List<Department> sortByName() {

        // SRAO: Replaced Collections.sort with Stream API's sorted() and a lambda comparator.
        return findAll().stream()
                .sorted(Comparator.comparing(Department::getName))
                .collect(Collectors.toCollection(ArrayList::new));

    }



    /**
     * Legacy report generation.
     */
    public String generateDepartmentSummary() {

        // SRAO: Replaced StringBuffer concatenation with Stream API and Collectors.joining().
        return departments.values().stream()
                .map(department -> department.getId() + " - " + department.getName() + "\n")
                .collect(Collectors.joining(""));

    }


}