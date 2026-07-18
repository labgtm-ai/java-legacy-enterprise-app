package com.company.legacy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional; // SRAO: Added Optional import
import java.util.stream.Collectors; // SRAO: Added Collectors import for Stream API

import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private DepartmentDAO departmentDAO;




    @Override
    public List<DepartmentResponse> getAllDepartments() {


        List<Department> departments =
                departmentDAO.findAll();

        // SRAO: Replaced traditional for-loop with Stream API for mapping
        return departments.stream()
                .map(this::convertToResponse)
                .toList(); // Java 16+ toList() for immutable list

    }





    @Override
    public DepartmentResponse getDepartmentById(
            Integer id) {


        Department department =
                departmentDAO.findById(id);

        // SRAO: Replaced explicit null check with Optional.orElseThrow
        return Optional.ofNullable(department)
                .map(this::convertToResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found with id : " + id));

    }





    @Override
    public DepartmentResponse createDepartment(
            Department department) {

        // SRAO: Replaced explicit null check with Optional.orElseThrow
        Department nonNullDepartment = Optional.ofNullable(department)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Department cannot be null"));


        nonNullDepartment.setActive(true);


        nonNullDepartment.setCreatedDate(
                new Date());


        nonNullDepartment.setLastModifiedDate(
                new Date());



        Department saved =
                departmentDAO.save(
                        nonNullDepartment);



        return convertToResponse(saved);

    }





    @Override
    public DepartmentResponse updateDepartment(
            Integer id,
            Department department) {



        Department existing =
                departmentDAO.findById(id);

        // SRAO: Replaced explicit null check with Optional.orElseThrow
        Department nonNullExisting = Optional.ofNullable(existing)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department not found : " + id));


        nonNullExisting.setName(
                department.getName());


        nonNullExisting.setLocation(
                department.getLocation());


        nonNullExisting.setDescription(
                department.getDescription());


        nonNullExisting.setActive(
                department.getActive());



        nonNullExisting.setLastModifiedDate(
                new Date());



        Department updated =
                departmentDAO.update(nonNullExisting);



        return convertToResponse(updated);

    }





    @Override
    public void deleteDepartment(
            Integer id) {



        Department department =
                departmentDAO.findById(id);

        // SRAO: Replaced explicit null check with Optional.orElseThrow
        Optional.ofNullable(department)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Department does not exist : " + id));


        departmentDAO.delete(id);

    }





    @Override
    public List<DepartmentResponse> searchDepartments(
            String name) {



        List<Department> departments =
                departmentDAO.searchByName(name);


        // SRAO: Replaced enhanced for-loop with Stream API for mapping
        return departments.stream()
                .map(this::convertToResponse)
                .toList(); // Java 16+ toList() for immutable list

    }





    @Override
    public String generateDepartmentReport() {



        List<Department> departments =
                departmentDAO.findAll();



        // SRAO: Replaced traditional for-loop with Stream API and Collectors.joining
        String reportBody = departments.stream()
                .map(department -> department.getId() + " - " + department.getName())
                .collect(Collectors.joining("\n"));

        return "Department Report\n" +
               "==================\n" +
               reportBody + "\n"; // Ensure final newline matches original behavior

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

        // SRAO: Replaced explicit null check with Optional.map and orElse
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
