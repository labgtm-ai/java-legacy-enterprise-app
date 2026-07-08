package com.company.legacy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



        List<DepartmentResponse> responseList =
                new ArrayList<DepartmentResponse>();



        for(int i = 0;
            i < departments.size();
            i++) {


            Department department =
                    departments.get(i);



            responseList.add(
                    convertToResponse(department));

        }



        return responseList;

    }





    @Override
    public DepartmentResponse getDepartmentById(
            Integer id) {


        Department department =
                departmentDAO.findById(id);



        if(department == null) {


            throw new ResourceNotFoundException(
                    "Department not found with id : "
                            + id);

        }



        return convertToResponse(department);

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



        Department existing =
                departmentDAO.findById(id);



        if(existing == null) {


            throw new ResourceNotFoundException(
                    "Department not found : "
                            + id);

        }



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



        Department department =
                departmentDAO.findById(id);



        if(department == null) {


            throw new ResourceNotFoundException(
                    "Department does not exist : "
                            + id);

        }



        departmentDAO.delete(id);

    }





    @Override
    public List<DepartmentResponse> searchDepartments(
            String name) {



        List<Department> departments =
                departmentDAO.searchByName(name);



        List<DepartmentResponse> responseList =
                new ArrayList<DepartmentResponse>();



        for(Department department :
                departments) {


            responseList.add(
                    convertToResponse(department));

        }



        return responseList;

    }





    @Override
    public String generateDepartmentReport() {



        List<Department> departments =
                departmentDAO.findAll();



        StringBuffer buffer =
                new StringBuffer();



        buffer.append(
                "Department Report\n");


        buffer.append(
                "==================\n");



        for(int i = 0;
            i < departments.size();
            i++) {



            Department department =
                    departments.get(i);



            buffer.append(
                    department.getId());


            buffer.append(" - ");


            buffer.append(
                    department.getName());


            buffer.append("\n");

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



        if(department == null) {

            return null;

        }



        DepartmentResponse response =
                new DepartmentResponse();



        response.setId(
                department.getId());


        response.setName(
                department.getName());


        response.setLocation(
                department.getLocation());


        response.setActive(
                department.getActive());



        return response;

    }


}