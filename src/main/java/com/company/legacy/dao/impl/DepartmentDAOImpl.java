package com.company.legacy.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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


        Iterator<Map.Entry<Integer, Department>> iterator =
                departments.entrySet().iterator();



        while (iterator.hasNext()) {


            Map.Entry<Integer, Department> entry =
                    iterator.next();


            Department department =
                    entry.getValue();


            if (department != null) {

                departmentList.add(department);

            }

        }


        return departmentList;

    }



    @Override
    public synchronized Department findById(Integer id) {


        if (id == null) {

            return null;

        }


        Department department =
                departments.get(id);


        return department;

    }



    @Override
    public synchronized Department save(
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
    public synchronized Department update(
            Department department) {


        if (department == null
                || department.getId() == null) {


            return null;

        }



        Department existing =
                departments.get(
                        department.getId());



        if (existing != null) {


            departments.put(
                    department.getId(),
                    department);


            return department;

        }



        return null;

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


        if (name == null) {

            return result;

        }



        Iterator<Department> iterator =
                departments.values()
                        .iterator();



        while (iterator.hasNext()) {


            Department department =
                    iterator.next();



            if (department != null
                    && department.getName() != null) {


                if (department.getName()
                        .toLowerCase()
                        .contains(
                                name.toLowerCase())) {


                    result.add(department);

                }

            }

        }


        return result;

    }



    @Override
    public int count() {


        int count = 0;



        for (Integer key :
                departments.keySet()) {


            if (departments.get(key) != null) {

                count++;

            }

        }


        return count;

    }



    /**
     * Legacy sorting method.
     */
    public List<Department> sortByName() {


        List<Department> list =
                findAll();



        Collections.sort(
                list,
                new Comparator<Department>() {


                    @Override
                    public int compare(
                            Department d1,
                            Department d2) {


                        return d1.getName()
                                .compareTo(
                                        d2.getName());

                    }

                });



        return list;

    }



    /**
     * Legacy report generation.
     */
    public String generateDepartmentSummary() {


        StringBuffer buffer =
                new StringBuffer();



        Iterator<Department> iterator =
                departments.values()
                        .iterator();



        while (iterator.hasNext()) {


            Department department =
                    iterator.next();



            if (department != null) {


                buffer.append(
                        department.getId());


                buffer.append(" - ");


                buffer.append(
                        department.getName());


                buffer.append("\n");

            }

        }


        return buffer.toString();

    }


}