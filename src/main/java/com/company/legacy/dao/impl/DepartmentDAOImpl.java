package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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


    // SRAO: Replaced Hashtable with ConcurrentHashMap for better concurrency and performance.
    private ConcurrentHashMap<Integer, Department> departments;


    public DepartmentDAOImpl() {
        // SRAO: Converted the Hashtable returned by FakeDatabase to a ConcurrentHashMap.
        departments = new ConcurrentHashMap<>(FakeDatabase.getDepartments());
    }



    @Override
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public List<Department> findAll() {
        // SRAO: Replaced explicit null check with Optional and streams for filtering non-null departments.
        return departments.values().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }



    @Override
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public Department findById(Integer id) {
        // SRAO: Replaced explicit null check with Optional for return value.
        return Optional.ofNullable(id)
                .map(departments::get)
                .orElse(null);
    }



    @Override
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public Department save(
            Department department) {


        if (department == null) {

            return null;

        }


        if (department.getId() == null) {


            int nextId =
                    departments.size() + 1;


            department.setId(nextId);

        }



        departments.put(
                department.getId(),
                department);



        return department;

    }



    @Override
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public Department update(
            Department department) {


        if (department == null
                || department.getId() == null) {


            return null;

        }


        // SRAO: Replaced explicit null check with Optional for conditional update.
        return Optional.ofNullable(departments.get(department.getId()))
                .map(existing -> {
                    departments.put(department.getId(), department);
                    return department;
                })
                .orElse(null);
    }



    @Override
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public void delete(
            Integer id) {


        if (id == null) {

            return;

        }


        if (departments.containsKey(id)) {


            departments.remove(id);

        }


    }



    @Override
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public List<Department> searchByName(
            String name) {


        // SRAO: Replaced explicit null checks with Optional and streams for filtering and processing.
        if (name == null) {
            return new ArrayList<>();
        }

        final String lowerCaseName = name.toLowerCase();
        return departments.values().stream()
                .filter(Objects::nonNull)
                .filter(d -> Optional.ofNullable(d.getName())
                        .map(n -> n.toLowerCase().contains(lowerCaseName))
                        .orElse(false))
                .collect(Collectors.toList());
    }



    @Override
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public int count() {
        // SRAO: Replaced explicit null check with stream filtering for counting non-null departments.
        return (int) departments.values().stream()
                .filter(Objects::nonNull)
                .count();
    }



    /**
     * Legacy sorting method.
     */
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public List<Department> sortByName() {
        // SRAO: Replaced Collections.sort with Stream API for sorting.
        return findAll().stream()
                .sorted(Comparator.comparing(Department::getName))
                .toList();
    }



    /**
     * Legacy report generation.
     */
    // SRAO: Removed synchronized keyword as ConcurrentHashMap handles thread safety internally.
    public String generateDepartmentSummary() {
        // SRAO: Replaced explicit null check with stream filtering and modern StringJoiner for summary generation.
        return departments.values().stream()
                .filter(Objects::nonNull)
                .map(d -> d.getId() + " - " + d.getName())
                .collect(Collectors.joining("\n"));
    }
}