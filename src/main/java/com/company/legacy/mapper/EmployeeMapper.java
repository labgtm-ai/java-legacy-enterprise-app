package com.company.legacy.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Objects;

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



        // SRAO: Replaced explicit null check with Optional.ofNullable for department.
        Optional.ofNullable(employee.getDepartment()).ifPresent(dept -> {
            DepartmentResponse departmentResponse =
                    new DepartmentResponse();


            departmentResponse.setId(
                    dept.getId());


            departmentResponse.setName(
                    dept.getName());


            departmentResponse.setLocation(
                    dept.getLocation());


            departmentResponse.setActive(
                    dept.getActive());



            response.setDepartment(
                    departmentResponse);

        });



        // SRAO: Replaced explicit null check with Optional.ofNullable for address.
        Optional.ofNullable(employee.getAddress()).ifPresent(address -> {
            response.setCity(
                    address.getCity());


            response.setCountry(
                    address.getCountry());

        });



        return response;

    }





    /**
     * Convert list of Employee entities.
     *
     * Legacy implementation using indexed loop.
     */
    public List<EmployeeResponse> toResponseList(
            List<Employee> employees) {


        // SRAO: Replaced traditional for-loop with Stream API for collection processing.
        if (employees == null) {
            return new ArrayList<>();
        }

        return employees.stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

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



        // SRAO: Replaced explicit null check with Optional.ofNullable.
        Optional.ofNullable(request.getFirstName()).ifPresent(employee::setFirstName);



        // SRAO: Replaced explicit null check with Optional.ofNullable.
        Optional.ofNullable(request.getLastName()).ifPresent(employee::setLastName);



        // SRAO: Replaced explicit null check with Optional.ofNullable.
        Optional.ofNullable(request.getEmail()).ifPresent(employee::setEmail);



        // SRAO: Replaced explicit null check with Optional.ofNullable.
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(employee::setPhoneNumber);



        // SRAO: Replaced explicit null check with Optional.ofNullable.
        Optional.ofNullable(request.getDesignation()).ifPresent(employee::setDesignation);



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


        // SRAO: Replaced explicit null checks with Optional.ofNullable and orElse.
        String firstName = Optional.ofNullable(employee.getFirstName()).orElse("");
        String lastName = Optional.ofNullable(employee.getLastName()).orElse("");



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



        // SRAO: Replaced explicit null check with Optional.ofNullable.
        Optional.ofNullable(employee.getSkills())
                .map(ArrayList::new)
                .ifPresent(copy::setSkills);



        return copy;

    }


}
