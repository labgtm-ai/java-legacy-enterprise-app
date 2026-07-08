package com.company.legacy.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.company.legacy.dto.EmployeeRequest;
import com.company.legacy.dto.EmployeeResponse;
import com.company.legacy.dto.DepartmentResponse;
import com.company.legacy.entity.Address;
import com.company.legacy.entity.Department;
import com.company.legacy.entity.Employee;


/**
 * Legacy mapper class for Employee conversion.
 *
 * Responsible for converting Employee entity objects
 * into DTO objects and vice versa.
 */
@Component
public class EmployeeMapper {


    /**
     * Convert Employee entity to EmployeeResponse DTO.
     *
     * @param employee employee entity
     * @return employee response
     */
    public EmployeeResponse toResponse(
            Employee employee) {


        if(employee == null) {

            return null;

        }



        EmployeeResponse response =
                new EmployeeResponse();



        response.setId(
                employee.getId());


        response.setEmployeeCode(
                employee.getEmployeeCode());


        response.setFullName(
                buildFullName(employee));


        response.setEmail(
                employee.getEmail());


        response.setPhoneNumber(
                employee.getPhoneNumber());


        response.setDesignation(
                employee.getDesignation());


        response.setSalary(
                employee.getSalary());


        response.setJoiningDate(
                employee.getJoiningDate());


        response.setStatus(
                employee.getStatus());


        response.setManager(
                employee.isManager());



        response.setSkills(
                employee.getSkills());



        if(employee.getDepartment() != null) {


            DepartmentResponse department =
                    new DepartmentResponse();



            department.setId(
                    employee.getDepartment()
                            .getId());


            department.setName(
                    employee.getDepartment()
                            .getName());


            department.setLocation(
                    employee.getDepartment()
                            .getLocation());


            department.setActive(
                    employee.getDepartment()
                            .getActive());



            response.setDepartment(
                    department);

        }



        if(employee.getAddress() != null) {


            response.setCity(
                    employee.getAddress()
                            .getCity());


            response.setCountry(
                    employee.getAddress()
                            .getCountry());

        }



        return response;

    }





    /**
     * Convert list of Employee entities.
     *
     * Legacy implementation using indexed loop.
     */
    public List<EmployeeResponse> toResponseList(
            List<Employee> employees) {


        List<EmployeeResponse> responseList =
                new ArrayList<EmployeeResponse>();



        if(employees == null) {

            return responseList;

        }



        for(int i = 0;
            i < employees.size();
            i++) {


            Employee employee =
                    employees.get(i);



            EmployeeResponse response =
                    toResponse(employee);



            if(response != null) {

                responseList.add(response);

            }

        }



        return responseList;

    }





    /**
     * Convert request DTO into entity.
     */
    public Employee toEntity(
            EmployeeRequest request) {


        if(request == null) {

            return null;

        }



        Employee employee =
                new Employee();



        employee.setEmployeeCode(
                request.getEmployeeCode());


        employee.setFirstName(
                request.getFirstName());


        employee.setLastName(
                request.getLastName());


        employee.setEmail(
                request.getEmail());


        employee.setPhoneNumber(
                request.getPhoneNumber());


        employee.setDesignation(
                request.getDesignation());


        employee.setSalary(
                request.getSalary());


        employee.setJoiningDate(
                request.getJoiningDate());


        employee.setStatus(
                request.getStatus());


        employee.setManager(
                request.isManager());



        employee.setSkills(
                request.getSkills());



        employee.setAddress(
                request.getAddress());



        return employee;

    }





    /**
     * Update existing entity from request.
     *
     * Legacy partial update style.
     */
    public void updateEntity(
            EmployeeRequest request,
            Employee employee) {


        if(request == null
                || employee == null) {

            return;

        }



        if(request.getFirstName() != null) {

            employee.setFirstName(
                    request.getFirstName());

        }



        if(request.getLastName() != null) {

            employee.setLastName(
                    request.getLastName());

        }



        if(request.getEmail() != null) {

            employee.setEmail(
                    request.getEmail());

        }



        if(request.getPhoneNumber() != null) {

            employee.setPhoneNumber(
                    request.getPhoneNumber());

        }



        if(request.getDesignation() != null) {

            employee.setDesignation(
                    request.getDesignation());

        }



        if(request.getSalary() > 0) {

            employee.setSalary(
                    request.getSalary());

        }



        employee.setManager(
                request.isManager());



    }





    /**
     * Build employee full name.
     */
    private String buildFullName(
            Employee employee) {


        String firstName = "";

        String lastName = "";



        if(employee.getFirstName() != null) {

            firstName =
                    employee.getFirstName();

        }



        if(employee.getLastName() != null) {

            lastName =
                    employee.getLastName();

        }



        return firstName + " " + lastName;

    }





    /**
     * Copy Employee object.
     *
     * Legacy manual cloning approach.
     */
    public Employee cloneEmployee(
            Employee employee) {


        if(employee == null) {

            return null;

        }



        Employee copy =
                new Employee();



        copy.setId(
                employee.getId());


        copy.setEmployeeCode(
                employee.getEmployeeCode());


        copy.setFirstName(
                employee.getFirstName());


        copy.setLastName(
                employee.getLastName());


        copy.setEmail(
                employee.getEmail());


        copy.setPhoneNumber(
                employee.getPhoneNumber());


        copy.setDesignation(
                employee.getDesignation());


        copy.setSalary(
                employee.getSalary());


        copy.setDepartment(
                employee.getDepartment());


        copy.setAddress(
                employee.getAddress());


        copy.setJoiningDate(
                employee.getJoiningDate());


        copy.setStatus(
                employee.getStatus());


        copy.setManager(
                employee.isManager());



        if(employee.getSkills() != null) {


            copy.setSkills(
                    new ArrayList<String>(
                            employee.getSkills()));

        }



        return copy;

    }


}