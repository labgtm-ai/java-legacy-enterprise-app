package com.company.legacy.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Response DTO returned by Employee REST APIs.
 *
 * This class represents the legacy Java 8 style
 * API response object.
 */
public class EmployeeResponse implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String employeeCode;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String designation;

    private double salary;

    private DepartmentResponse department;

    private String city;

    private String country;

    private Date joiningDate;

    private String status;

    private boolean manager;

    private List<String> skills;


    public EmployeeResponse() {

    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
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


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {

        if (fullName != null) {
            this.fullName = fullName.trim();
        } else {
            this.fullName = null;
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


    public DepartmentResponse getDepartment() {
        return department;
    }


    public void setDepartment(DepartmentResponse department) {
        this.department = department;
    }


    public String getCity() {
        return city;
    }


    public void setCity(String city) {

        if (city != null) {
            this.city = city.trim();
        } else {
            this.city = null;
        }

    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {

        if (country != null) {
            this.country = country.trim();
        } else {
            this.country = null;
        }

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


        buffer.append("EmployeeResponse [");

        buffer.append("id=");
        buffer.append(id);

        buffer.append(", employeeCode=");
        buffer.append(employeeCode);

        buffer.append(", fullName=");
        buffer.append(fullName);

        buffer.append(", designation=");
        buffer.append(designation);

        buffer.append(", status=");
        buffer.append(status);

        buffer.append("]");


        return buffer.toString();

    }

}