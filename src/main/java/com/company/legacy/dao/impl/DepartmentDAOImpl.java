package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Objects;
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
        // SRAO: Replaced explicit null check with stream API filter
        return departments.values().stream()
                          .filter(Objects::nonNull)
                          .collect(Collectors.toList());
    }



    @Override
    public synchronized Department findById(Integer id) {
        // SRAO: Replaced explicit null check with Optional.ofNullable
        return Optional.ofNullable(id)
                       .map(departments::get)
                       .orElse(null);
    }



    @Override
    public synchronized Department save(
            Department department) {
        // SRAO: Replaced explicit null check with Optional.ofNullable
        return Optional.ofNullable(department)
                       .map(d -> {
                           if (d.getId() == null) {
                               int nextId = departments.size() + 1;
                               d.setId(nextId);
                           }
                           departments.put(d.getId(), d);
                           return d;
                       })
                       .orElse(null);
    }



    @Override
    public synchronized Department update(
            Department department) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and filter
        return Optional.ofNullable(department)
                       .filter(d -> d.getId() != null)
                       .map(d -> {
                           Department existing = departments.get(d.getId());
                           if (existing != null) {
                               departments.put(d.getId(), d);
                               return d;
                           }
                           return null;
                       })
                       .orElse(null);
    }



    @Override
    public synchronized void delete(
            Integer id) {
        // SRAO: Replaced explicit null check with Optional.ofNullable.ifPresent
        Optional.ofNullable(id)
                .ifPresent(departments::remove);
    }



    @Override
    public synchronized List<Department> searchByName(
            String name) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and stream API
        return Optional.ofNullable(name)
                       .map(n -> departments.values().stream()
                                            .filter(Objects::nonNull)
                                            .filter(d -> d.getName() != null && d.getName().toLowerCase().contains(n.toLowerCase()))
                                            .collect(Collectors.toList()))
                       .orElse(new ArrayList<>());
    }



    @Override
    public int count() {
        // SRAO: Replaced explicit null check with stream API filter
        return (int) departments.values().stream()
                                .filter(Objects::nonNull)
                                .count();
    }



    /**
     * Legacy sorting method.
     */
    public List<Department> sortByName() {
        // SRAO: Replaced Collections.sort with Stream API sorted and Comparator.comparing
        return findAll().stream()
                        .sorted(Comparator.comparing(Department::getName))
                        .collect(Collectors.toList());
    }



    /**
     * Legacy report generation.
     */
    public String generateDepartmentSummary() {
        // SRAO: Replaced StringBuilder with Stream API map and Collectors.joining
        return departments.values().stream()
                          .filter(Objects::nonNull)
                          .map(department -> department.getId() + " - " + department.getName())
                          .collect(Collectors.joining("\n"));
    }


}
