package com.company.legacy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String designation;

    private double salary;

    private Department department;

    private Address address;

    private Date joiningDate;

    private Date lastModifiedDate;

    private String status;

    private boolean manager;

    private List<String> skills;

    public Employee() {

        this.skills = new ArrayList<String>();

    }

    public Employee(Integer id,
                    String employeeCode,
                    String firstName,
                    String lastName) {

        this();

        this.id = id;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = "ACTIVE";
        this.joiningDate = new Date();
        this.lastModifiedDate = new Date();

    }

    public Employee(Integer id,
                    String employeeCode,
                    String firstName,
                    String lastName,
                    String email,
                    String phoneNumber,
                    String designation,
                    double salary,
                    Department department,
                    Address address,
                    Date joiningDate,
                    Date lastModifiedDate,
                    String status,
                    boolean manager,
                    List<String> skills) {

        this.id = id;
        this.employeeCode = employeeCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.designation = designation;
        this.salary = salary;
        this.department = department;
        this.address = address;
        this.joiningDate = joiningDate;
        this.lastModifiedDate = lastModifiedDate;
        this.status = status;
        this.manager = manager;
        // SRAO: Replaced explicit null check with Optional.ofNullable and orElseGet for skills initialization.
        this.skills = Optional.ofNullable(skills).orElseGet(ArrayList::new);

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
        this.employeeCode = employeeCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        StringBuilder builder = new StringBuilder();

        builder.append("Employee [");
        builder.append("id=").append(id);
        builder.append(", employeeCode=").append(employeeCode);
        builder.append(", firstName=").append(firstName);
        builder.append(", lastName=").append(lastName);
        builder.append(", designation=").append(designation);
        builder.append(", status=").append(status);

        if (department != null) {
            builder.append(", department=");
            builder.append(department.getName());
        }

        builder.append("]");

        return builder.toString();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (obj == null)
            return false;

        // SRAO: Using pattern matching for instanceof to safely cast and declare 'other'
        if (!(obj instanceof Employee other))
            return false;

        if (id == null) {
            return other.id == null;
        }

        return id.equals(other.id);
    }

    @Override
    public int hashCode() {

        final int prime = 31;

        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }
}