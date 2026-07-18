package com.company.legacy.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.company.legacy.entity.Address;


/**
 * Request object used for creating/updating employees.
 *
 * Legacy DTO style used in Java 8 enterprise applications.
 */
public class EmployeeRequest implements Serializable {

    private static final long serialVersionUID = 1L;


    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String designation;

    private double salary;

    private Integer departmentId;

    private Address address;

    private Date joiningDate;

    private String status;

    private boolean manager;

    private List<String> skills;


    public EmployeeRequest() {

    }


    public String getEmployeeCode() {
        return employeeCode;
    }


    public void setEmployeeCode(String employeeCode) {

        if (employeeCode != null) {
            this.employeeCode = employeeCode.trim();
        } else {
            this.employeeCode = null;
        }

    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {

        if (firstName != null) {
            this.firstName = firstName.trim();
        } else {
            this.firstName = null;
        }

    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {

        if (lastName != null) {
            this.lastName = lastName.trim();
        } else {
            this.lastName = null;
        }

    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {

        if (email != null) {
            this.email = email.trim();
        } else {
            this.email = null;
        }

    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {

        if (phoneNumber != null) {
            this.phoneNumber = phoneNumber.trim();
        } else {
            this.phoneNumber = null;
        }

    }


    public String getDesignation() {
        return designation;
    }


    public void setDesignation(String designation) {

        if (designation != null) {
            this.designation = designation.trim();
        } else {
            this.designation = null;
        }

    }


    public double getSalary() {
        return salary;
    }


    public void setSalary(double salary) {

        if (salary < 0) {
            salary = 0;
        }

        this.salary = salary;

    }


    public Integer getDepartmentId() {
        return departmentId;
    }


    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }


    public Address getAddress() {
        return address;
    }


    public void setAddress(Address address) {
        this.address = address;
    }


    public Date getJoiningDate() {
        return joiningDate;
    }


    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {

        if (status != null) {
            this.status = status.trim();
        } else {
            this.status = null;
        }

    }


    public boolean isManager() {
        return manager;
    }


    public void setManager(boolean manager) {
        this.manager = manager;
    }


    public List<String> getSkills() {
        return skills;
    }


    public void setSkills(List<String> skills) {
        this.skills = skills;
    }


    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder(); // SRAO: Replaced StringBuffer with StringBuilder for performance.

        buffer.append("EmployeeRequest [");
        buffer.append("employeeCode=");
        buffer.append(employeeCode);

        buffer.append(", firstName=");
        buffer.append(firstName);

        buffer.append(", lastName=");
        buffer.append(lastName);

        buffer.append(", email=");
        buffer.append(email);

        buffer.append(", designation=");
        buffer.append(designation);

        buffer.append(", salary=");
        buffer.append(salary);

        buffer.append(", departmentId=");
        buffer.append(departmentId);

        buffer.append("]");


        return buffer.toString();

    }

}
