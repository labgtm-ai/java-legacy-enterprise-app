package com.company.legacy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // SRAO: Added for Stream API operations.

import org.springframework.stereotype.Service;

import com.company.legacy.dao.DepartmentDAO;
import com.company.legacy.dto.DepartmentResponse;
import com.company.legacy.entity.Department;
import com.company.legacy.exception.ResourceNotFoundException;
import com.company.legacy.service.DepartmentService;


/**
 * Legacy implementation of Department Service.
 *
 * Contains business logic and manual mapping.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentDAO departmentDAO; // SRAO: Replaced field injection with constructor injection.

    public DepartmentServiceImpl(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }




    @Override
    public List<DepartmentResponse> getAllDepartments() {


        List<Department> departments =
                departmentDAO.findAll();

        // SRAO: Replaced traditional for-loop with Stream API.
        return departments.stream()
                          .map(this::convertToResponse)
                          .toList(); // Java 16+ for immutable list.

    }





    @Override
    public DepartmentResponse getDepartmentById(
            Integer id) {

        // SRAO: Replaced null check with Optional.
        return Optional.ofNullable(departmentDAO.findById(id))
                       .map(this::convertToResponse)
                       .orElseThrow(() -> new ResourceNotFoundException("Department not found with id : " + id));
    }





    @Override
    public DepartmentResponse createDepartment(
            Department department) {



        if(department == null) {


            throw new IllegalArgumentException(
                    "Department cannot be null");

        }



        department.setActive(true);


        department.setCreatedDate(
                new Date());


        department.setLastModifiedDate(
                new Date());



        Department saved =
                departmentDAO.save(
                        department);



        return convertToResponse(saved);

    }





    @Override
    public DepartmentResponse updateDepartment(
            Integer id,
            Department department) {


        // SRAO: Replaced null check with Optional.
        Department existing = Optional.ofNullable(departmentDAO.findById(id))
                                  .orElseThrow(() -> new ResourceNotFoundException("Department not found : " + id));


        existing.setName(
                department.getName());


        existing.setLocation(
                department.getLocation());


        existing.setDescription(
                department.getDescription());


        existing.setActive(
                department.getActive());



        existing.setLastModifiedDate(
                new Date());



        Department updated =
                departmentDAO.update(existing);



        return convertToResponse(updated);

    }





    @Override
    public void deleteDepartment(
            Integer id) {


        // SRAO: Replaced null check with Optional.
        Optional.ofNullable(departmentDAO.findById(id))
                .ifPresentOrElse(
                    d -> departmentDAO.delete(id),
                    () -> { throw new ResourceNotFoundException("Department does not exist : " + id); }
                );
    }





    @Override
    public List<DepartmentResponse> searchDepartments(
            String name) {



        List<Department> departments =
                departmentDAO.searchByName(name);

        // SRAO: Replaced enhanced for-loop with Stream API.
        return departments.stream()
                          .map(this::convertToResponse)
                          .toList(); // Java 16+ for immutable list.

    }





    @Override
    public String generateDepartmentReport() {



        List<Department> departments =
                departmentDAO.findAll();



        StringBuilder buffer = // SRAO: Replaced StringBuffer with StringBuilder for performance in a non-thread-safe context.
                new StringBuilder();



        buffer.append(
                "Department Report\n");


        buffer.append(
                "==================\n");

        // SRAO: Replaced traditional for-loop with Stream API for report generation.
        String departmentDetails = departments.stream()
            .map(department -> department.getId() + " - " + department.getName() + "\n")
            .collect(Collectors.joining());

        buffer.append(departmentDetails);

        return buffer.toString();

    }





    @Override
    public int getDepartmentCount() {


        return departmentDAO.count();

    }






    /**
     * Entity to DTO conversion.
     */
    private DepartmentResponse convertToResponse(
            Department department) {

        // SRAO: Replaced null check with Optional.
        return Optional.ofNullable(department)
                       .map(d -> {
                           DepartmentResponse response = new DepartmentResponse();
                           response.setId(d.getId());
                           response.setName(d.getName());
                           response.setLocation(d.getLocation());
                           response.setActive(d.getActive());
                           return response;
                       })
                       .orElse(null);
    }


}