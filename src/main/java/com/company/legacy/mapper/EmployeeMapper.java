package com.company.legacy.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

        // SRAO: Replaced explicit null check with Optional.ofNullable for the employee object.
        return Optional.ofNullable(employee).map(emp -> {
            EmployeeResponse response =
                    new EmployeeResponse();

            response.setId(
                    emp.getId());

            response.setEmployeeCode(
                    emp.getEmployeeCode());

            response.setFullName(
                    buildFullName(emp));

            response.setEmail(
                    emp.getEmail());

            response.setPhoneNumber(
                    emp.getPhoneNumber());

            response.setDesignation(
                    emp.getDesignation());

            response.setSalary(
                    emp.getSalary());

            response.setJoiningDate(
                    emp.getJoiningDate());

            response.setStatus(
                    emp.getStatus());

            response.setManager(
                    emp.isManager());

            response.setSkills(
                    emp.getSkills());

            // SRAO: Replaced explicit null check with Optional.ofNullable for department.
            Optional.ofNullable(emp.getDepartment()).ifPresent(dept -> {
                DepartmentResponse department =
                        new DepartmentResponse();

                department.setId(
                        dept.getId());

                department.setName(
                        dept.getName());

                department.setLocation(
                        dept.getLocation());

                department.setActive(
                        dept.getActive());

                response.setDepartment(
                        department);
            });

            // SRAO: Replaced explicit null check with Optional.ofNullable for address.
            Optional.ofNullable(emp.getAddress()).ifPresent(address -> {
                response.setCity(
                        address.getCity());

                response.setCountry(
                        address.getCountry());
            });

            return response;
        }).orElse(null);
    }





    /**
     * Convert list of Employee entities.
     *
     * Legacy implementation using indexed loop.
     */
    public List<EmployeeResponse> toResponseList(
            List<Employee> employees) {

        // SRAO: Replaced explicit null check and indexed loop with Optional and Stream API's toList() collector.
        return Optional.ofNullable(employees)
                .orElse(List.of())
                .stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .toList();
    }





    /**
     * Convert request DTO into entity.
     */
    public Employee toEntity(
            EmployeeRequest request) {

        // SRAO: Replaced explicit null check with Optional.ofNullable for the request object.
        return Optional.ofNullable(request).map(req -> {
            Employee employee =
                    new Employee();

            employee.setEmployeeCode(
                    req.getEmployeeCode());

            employee.setFirstName(
                    req.getFirstName());

            employee.setLastName(
                    req.getLastName());

            employee.setEmail(
                    req.getEmail());

            employee.setPhoneNumber(
                    req.getPhoneNumber());

            employee.setDesignation(
                    req.getDesignation());

            employee.setSalary(
                    req.getSalary());

            employee.setJoiningDate(
                    req.getJoiningDate());

            employee.setStatus(
                    req.getStatus());

            employee.setManager(
                    req.isManager());

            employee.setSkills(
                    req.getSkills());

            employee.setAddress(
                    req.getAddress());

            return employee;
        }).orElse(null);
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

        // SRAO: Replaced explicit null check with Optional.ofNullable for firstName.
        Optional.ofNullable(request.getFirstName()).ifPresent(employee::setFirstName);

        // SRAO: Replaced explicit null check with Optional.ofNullable for lastName.
        Optional.ofNullable(request.getLastName()).ifPresent(employee::setLastName);

        // SRAO: Replaced explicit null check with Optional.ofNullable for email.
        Optional.ofNullable(request.getEmail()).ifPresent(employee::setEmail);

        // SRAO: Replaced explicit null check with Optional.ofNullable for phoneNumber.
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(employee::setPhoneNumber);

        // SRAO: Replaced explicit null check with Optional.ofNullable for designation.
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

        // SRAO: Replaced explicit null checks with Optional.ofNullable and orElse for firstName.
        String firstName = Optional.ofNullable(employee.getFirstName()).orElse("");
        // SRAO: Replaced explicit null checks with Optional.ofNullable and orElse for lastName.
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

        // SRAO: Replaced explicit null check with Optional.ofNullable for the employee object.
        return Optional.ofNullable(employee).map(emp -> {
            Employee copy =
                    new Employee();

            copy.setId(
                    emp.getId());

            copy.setEmployeeCode(
                    emp.getEmployeeCode());

            copy.setFirstName(
                    emp.getFirstName());

            copy.setLastName(
                    emp.getLastName());

            copy.setEmail(
                    emp.getEmail());

            copy.setPhoneNumber(
                    emp.getPhoneNumber());

            copy.setDesignation(
                    emp.getDesignation());

            copy.setSalary(
                    emp.getSalary());

            copy.setDepartment(
                    emp.getDepartment());

            copy.setAddress(
                    emp.getAddress());

            copy.setJoiningDate(
                    emp.getJoiningDate());

            copy.setStatus(
                    emp.getStatus());

            copy.setManager(
                    emp.isManager());

            // SRAO: Replaced explicit null check with Optional.ofNullable for skills.
            Optional.ofNullable(emp.getSkills()).map(ArrayList::new).ifPresent(copy::setSkills);

            return copy;
        }).orElse(null);
    }

}