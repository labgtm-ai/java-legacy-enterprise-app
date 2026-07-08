package com.company.legacy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.legacy.dto.DepartmentResponse;
import com.company.legacy.entity.Department;
import com.company.legacy.service.DepartmentService;


/**
 * REST Controller for Department operations.
 *
 * Legacy Spring Boot controller implementation.
 */
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;




    /**
     * Get all departments.
     */
    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {


        List<DepartmentResponse> departments =
                departmentService.getAllDepartments();



        return new ResponseEntity<List<DepartmentResponse>>(
                departments,
                HttpStatus.OK);

    }





    /**
     * Get department by id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponse> getDepartmentById(
            @PathVariable("id") Integer id) {


        DepartmentResponse department =
                departmentService.getDepartmentById(id);



        return new ResponseEntity<DepartmentResponse>(
                department,
                HttpStatus.OK);

    }





    /**
     * Create department.
     */
    @PostMapping
    public ResponseEntity<DepartmentResponse> createDepartment(
            @RequestBody Department department) {


        DepartmentResponse response =
                departmentService.createDepartment(
                        department);



        return new ResponseEntity<DepartmentResponse>(
                response,
                HttpStatus.CREATED);

    }





    /**
     * Update department.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponse> updateDepartment(
            @PathVariable("id") Integer id,
            @RequestBody Department department) {


        DepartmentResponse response =
                departmentService.updateDepartment(
                        id,
                        department);



        return new ResponseEntity<DepartmentResponse>(
                response,
                HttpStatus.OK);

    }





    /**
     * Delete department.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(
            @PathVariable("id") Integer id) {


        departmentService.deleteDepartment(id);



        return new ResponseEntity<String>(
                "Department deleted successfully",
                HttpStatus.OK);

    }





    /**
     * Search departments by name.
     *
     * Example:
     * GET /api/departments/search/Engineering
     */
    @GetMapping("/search/{name}")
    public ResponseEntity<List<DepartmentResponse>> searchDepartments(
            @PathVariable("name") String name) {


        List<DepartmentResponse> departments =
                departmentService.searchDepartments(name);



        return new ResponseEntity<List<DepartmentResponse>>(
                departments,
                HttpStatus.OK);

    }





    /**
     * Generate department report.
     */
    @GetMapping("/report")
    public ResponseEntity<String> generateReport() {


        String report =
                departmentService.generateDepartmentReport();



        return new ResponseEntity<String>(
                report,
                HttpStatus.OK);

    }





    /**
     * Get department count.
     */
    @GetMapping("/count")
    public ResponseEntity<Integer> getCount() {


        int count =
                departmentService.getDepartmentCount();



        return new ResponseEntity<Integer>(
                count,
                HttpStatus.OK);

    }


}