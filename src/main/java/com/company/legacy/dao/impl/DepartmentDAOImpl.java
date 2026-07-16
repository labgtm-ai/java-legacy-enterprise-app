package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


        List<Department> departmentList =
                new ArrayList<Department>();


        departments.entrySet().forEach(entry ->
            Optional.ofNullable(entry.getValue()).ifPresent(departmentList::add)
        );


        return departmentList;

    }



    @Override
    public synchronized Department findById(Integer id) {


        return Optional.ofNullable(id)
                       .map(departments::get)
                       .orElse(null);

    }



    @Override
    public synchronized Department save(
            Department department) {


        return Optional.ofNullable(department)
                       .map(d -> {
                           if (d.getId() == null) {
                               int nextId = departments.size() + 1;
                               d.setId(nextId);
                           }
                           departments.put(d.getId(), d);
                           return d;
                       }).orElse(null);

    }



    @Override
    public synchronized Department update(
            Department department) {


        return Optional.ofNullable(department)
                       .filter(d -> d.getId() != null)
                       .flatMap(d -> Optional.ofNullable(departments.get(d.getId()))
                                             .map(existing -> {
                                                 departments.put(d.getId(), d);
                                                 return d;
                                             }))
                       .orElse(null);

    }



    @Override
    public synchronized void delete(
            Integer id) {


        if (id == null) {

            return;

        }


        if (departments.containsKey(id)) {


            departments.remove(id);

        }


    }



    @Override
    public synchronized List<Department> searchByName(
            String name) {


        List<Department> result =
                new ArrayList<Department>();


        String searchNameLower = Optional.ofNullable(name).map(String::toLowerCase).orElse(null);
        if (searchNameLower == null) {
            return result;
        }

        departments.values().forEach(department ->
            Optional.ofNullable(department)
                    .filter(d -> Optional.ofNullable(d.getName())
                                         .map(n -> n.toLowerCase().contains(searchNameLower))
                                         .orElse(false))
                    .ifPresent(result::add)
        );


        return result;

    }



    @Override
    public int count() {
        return (int) departments.values().stream()
                                .filter(Objects::nonNull)
                                .count();
    }



    /**
     * Legacy sorting method.
     */
    public List<Department> sortByName() {


        List<Department> list =
                findAll();



        Collections.sort(
                list,
                (d1, d2) -> Optional.ofNullable(d1.getName()).orElse("")
                                    .compareTo(Optional.ofNullable(d2.getName()).orElse(""))
        );



        return list;

    }



    /**
     * Legacy report generation.
     */
    public String generateDepartmentSummary() {


        StringBuilder buffer =
                new StringBuilder();



        departments.values().forEach(department ->
            Optional.ofNullable(department).ifPresent(d -> {
                buffer.append(d.getId());
                buffer.append(" - ");
                buffer.append(d.getName());
                buffer.append("\n");
            })
        );


        return buffer.toString();

    }


}