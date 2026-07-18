package com.company.legacy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors; // SRAO: Added for Collectors.joining

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



        // SRAO: Replaced traditional for-loop with Stream API for mapping and collecting
        return departments.stream()
                .map(this::convertToResponse)
                .toList();

    }





    @Override
    public DepartmentResponse getDepartmentById(
            Integer id) {


        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow
        Department department = Optional.ofNullable(departmentDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id : " + id));


        return convertToResponse(department);

    }





    @Override
    public DepartmentResponse createDepartment(
            Department department) {


        // SRAO: Replaced explicit null check for parameter with Optional.ofNullable and orElseThrow
        Department nonNullDepartment = Optional.ofNullable(department)
                .orElseThrow(() -> new IllegalArgumentException("Department cannot be null"));


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


        // SRAO: Replaced explicit null check for parameter with Optional.ofNullable and orElseThrow
        Department nonNullDepartment = Optional.ofNullable(department)
                .orElseThrow(() -> new IllegalArgumentException("Department cannot be null"));

        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow
        Department existing = Optional.ofNullable(departmentDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Department not found : " + id));


        existing.setName(
                nonNullDepartment.getName());


        existing.setLocation(
                nonNullDepartment.getLocation());


        existing.setDescription(
                nonNullDepartment.getDescription());


        existing.setActive(
                nonNullDepartment.getActive());



        existing.setLastModifiedDate(
                new Date());



        Department updated =
                departmentDAO.update(existing);



        return convertToResponse(updated);

    }





    @Override
    public void deleteDepartment(
            Integer id) {


        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseThrow
        Department department = Optional.ofNullable(departmentDAO.findById(id))
                .orElseThrow(() -> new ResourceNotFoundException("Department does not exist : " + id));


        departmentDAO.delete(id);

    }





    @Override
    public List<DepartmentResponse> searchDepartments(
            String name) {



        List<Department> departments =
                departmentDAO.searchByName(name);



        // SRAO: Replaced enhanced for-loop with Stream API for mapping and collecting
        return departments.stream()
                .map(this::convertToResponse)
                .toList();

    }





    @Override
    public String generateDepartmentReport() {



        List<Department> departments =
                departmentDAO.findAll();



        // SRAO: Replaced StringBuffer with StringBuilder and for-loop with Stream API for report generation
        StringBuilder buffer = new StringBuilder();
        buffer.append("Department Report\n");
        buffer.append("==================\n");

        departments.stream()
            .map(department -> department.getId() + " - " + department.getName())
            .forEach(line -> buffer.append(line).append("\n"));

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


        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse
        return Optional.ofNullable(department)
                .map(dept -> {
                    DepartmentResponse response = new DepartmentResponse();
                    response.setId(dept.getId());
                    response.setName(dept.getName());
                    response.setLocation(dept.getLocation());
                    response.setActive(dept.getActive());
                    return response;
                })
                .orElse(null);

    }


}