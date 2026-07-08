package com.company.legacy.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.company.legacy.dto.DepartmentResponse;
import com.company.legacy.entity.Department;


/**
 * Legacy mapper class for Department conversion.
 *
 * Converts Department entity objects into DTO objects.
 *
 * This represents the manual mapping approach commonly
 * used in older Java enterprise applications.
 */
@Component
public class DepartmentMapper {


    /**
     * Convert Department entity to response DTO.
     *
     * @param department department entity
     * @return department response
     */
    public DepartmentResponse toResponse(
            Department department) {


        if (department == null) {

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




    /**
     * Convert list of departments.
     *
     * Legacy implementation using traditional loop.
     *
     * @param departments department list
     * @return response list
     */
    public List<DepartmentResponse> toResponseList(
            List<Department> departments) {


        List<DepartmentResponse> responseList =
                new ArrayList<DepartmentResponse>();



        if (departments == null) {

            return responseList;

        }



        for(int i = 0;
            i < departments.size();
            i++) {


            Department department =
                    departments.get(i);



            DepartmentResponse response =
                    toResponse(department);



            if(response != null) {

                responseList.add(response);

            }

        }



        return responseList;

    }




    /**
     * Update Department entity fields from another object.
     *
     * Legacy style update method.
     */
    public void updateEntity(
            Department source,
            Department target) {



        if(source == null
                || target == null) {

            return;

        }



        if(source.getName() != null) {

            target.setName(
                    source.getName());

        }



        if(source.getLocation() != null) {

            target.setLocation(
                    source.getLocation());

        }



        if(source.getDescription() != null) {

            target.setDescription(
                    source.getDescription());

        }



        if(source.getActive() != null) {

            target.setActive(
                    source.getActive());

        }

    }




    /**
     * Creates a shallow copy.
     *
     * Legacy applications commonly used
     * manual cloning methods.
     */
    public Department cloneDepartment(
            Department department) {


        if(department == null) {

            return null;

        }



        Department copy =
                new Department();



        copy.setId(
                department.getId());


        copy.setName(
                department.getName());


        copy.setLocation(
                department.getLocation());


        copy.setDescription(
                department.getDescription());


        copy.setActive(
                department.getActive());


        copy.setCreatedDate(
                department.getCreatedDate());


        copy.setLastModifiedDate(
                department.getLastModifiedDate());



        return copy;

    }


}