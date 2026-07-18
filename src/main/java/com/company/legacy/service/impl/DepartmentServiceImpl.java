package com.company.legacy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.Instant;
import java.util.stream.Collectors; // SRAO: Added for Stream API operations

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


        // SRAO: Replaced traditional for-loop with Stream API for transformation.
        return departments.stream()
                .map(this::convertToResponse)
                .toList(); // Java 16+ toList() for immutable list

    }





    @Override
    public DepartmentResponse getDepartmentById(
            Integer id) {


        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow for better null handling.
        Department department = Optional.ofNullable(departmentDAO.findById(id))
                                    .orElseThrow(() -> new ResourceNotFoundException(
                                            "Department not found with id : " + id));



        return convertToResponse(department);

    }





    @Override
    public DepartmentResponse createDepartment(
            Department department) {



        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow for input validation.
        Department nonNullDepartment = Optional.ofNullable(department)
                                            .orElseThrow(() -> new IllegalArgumentException(
                                                    "Department cannot be null"));



        nonNullDepartment.setActive(true);


        // SRAO: Replaced java.util.Date with java.time.Instant for current timestamp.
        nonNullDepartment.setCreatedDate(
                Date.from(Instant.now()));


        // SRAO: Replaced java.util.Date with java.time.Instant for current timestamp.
        nonNullDepartment.setLastModifiedDate(
                Date.from(Instant.now()));



        Department saved =
                departmentDAO.save(
                        nonNullDepartment);



        return convertToResponse(saved);

    }





    @Override
    public DepartmentResponse updateDepartment(
            Integer id,
            Department department) {



        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow for resource existence.
        Department existing = Optional.ofNullable(departmentDAO.findById(id))
                                  .orElseThrow(() -> new ResourceNotFoundException(
                                          "Department not found : " + id));



        existing.setName(
                department.getName());


        existing.setLocation(
                department.getLocation());


        existing.setDescription(
                department.getDescription());


        existing.setActive(
                department.getActive());



        // SRAO: Replaced java.util.Date with java.time.Instant for current timestamp.
        existing.setLastModifiedDate(
                Date.from(Instant.now()));



        Department updated =
                departmentDAO.update(existing);



        return convertToResponse(updated);

    }





    @Override
    public void deleteDepartment(
            Integer id) {



        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow for resource existence.
        Optional.ofNullable(departmentDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department does not exist : " + id));



        departmentDAO.delete(id);

    }





    @Override
    public List<DepartmentResponse> searchDepartments(
            String name) {



        List<Department> departments =
                departmentDAO.searchByName(name);


        // SRAO: Replaced enhanced for-loop with Stream API for transformation.
        return departments.stream()
                .map(this::convertToResponse)
                .toList(); // Java 16+ toList() for immutable list

    }





    @Override
    public String generateDepartmentReport() {



        List<Department> departments =
                departmentDAO.findAll();


        // SRAO: Replaced traditional for-loop with Stream API and StringBuilder for report generation.
        StringBuilder buffer = new StringBuilder();
        buffer.append("Department Report\n");
        buffer.append("==================\n");

        String departmentEntries = departments.stream()
            .map(department -> department.getId() + " - " + department.getName())
            .collect(Collectors.joining("\n"));

        if (!departmentEntries.isEmpty()) {
            buffer.append(departmentEntries);
            buffer.append("\n"); // Ensure a trailing newline if entries exist, matching original behavior
        }

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



        // SRAO: Replaced explicit null check with Optional.ofNullable and map for cleaner conversion.
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