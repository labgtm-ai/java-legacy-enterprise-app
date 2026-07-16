package com.company.legacy.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.company.legacy.entity.Address;
import com.company.legacy.entity.Department;
import com.company.legacy.entity.Employee;
import com.company.legacy.repository.FakeDatabase;


/**
 * Initializes in-memory application data.
 *
 * This simulates database seed data in a legacy
 * enterprise Java application.
 */
@Component
public class DataInitializer {

    // Dedicated executor for initialization tasks to avoid blocking the common ForkJoinPool
    private final ExecutorService initializerExecutor = Executors.newFixedThreadPool(2);


    /**
     * Load sample data during application startup.
     */
    @PostConstruct
    public void initializeData() {
        // Trigger asynchronous loading of departments and employees
        CompletableFuture<Void> departmentsFuture = loadDepartmentsAsync();
        CompletableFuture<Void> employeesFuture = loadEmployeesAsync();

        // Wait for both asynchronous operations to complete.
        // This ensures that the @PostConstruct method finishes only after data is loaded,
        // maintaining the original synchronous startup behavior from Spring's perspective,
        // but offloading the blocking I/O to a separate thread pool.
        CompletableFuture.allOf(departmentsFuture, employeesFuture).join();
    }


    private CompletableFuture<Void> loadDepartmentsAsync() {
        return CompletableFuture.runAsync(() -> {
            List<Department> departments = new ArrayList<>();

            Department engineering = new Department();
            engineering.setId(1);
            engineering.setName("Engineering");
            engineering.setLocation("Building A");
            engineering.setDescription("Software development team");
            engineering.setActive(true);
            engineering.setCreatedDate(new Date());
            engineering.setLastModifiedDate(new Date());

            Department finance = new Department();
            finance.setId(2);
            finance.setName("Finance");
            finance.setLocation("Building B");
            finance.setDescription("Finance and accounting team");
            finance.setActive(true);
            finance.setCreatedDate(new Date());
            finance.setLastModifiedDate(new Date());

            departments.add(engineering);
            departments.add(finance);

            // This is the simulated blocking I/O operation being wrapped
            FakeDatabase.initializeDepartments(departments);
        }, initializerExecutor); // Use the dedicated executor
    }


    private CompletableFuture<Void> loadEmployeesAsync() {
        return CompletableFuture.runAsync(() -> {
            List<Employee> employees = new ArrayList<>();

            Employee employee1 = new Employee();
            employee1.setId(1001);
            employee1.setEmployeeCode("EMP1001");
            employee1.setFirstName("John");
            employee1.setLastName("Smith");
            employee1.setEmail("john.smith@company.com");
            employee1.setPhoneNumber("555-1001");
            employee1.setDesignation("Senior Developer");
            employee1.setSalary(95000);
            employee1.setStatus("ACTIVE");
            employee1.setManager(false);
            employee1.setJoiningDate(new Date());
            employee1.setSkills(Arrays.asList("Java", "Spring Boot", "Kafka"));
            // This is also a simulated blocking I/O operation (reading from FakeDatabase)
            employee1.setDepartment(FakeDatabase.getDepartments().get(1));

            Address address1 = new Address();
            address1.setAddressLine1("100 Main Street");
            address1.setCity("Raleigh");
            address1.setState("NC");
            address1.setCountry("USA");
            address1.setZipCode("27601");
            employee1.setAddress(address1);

            Employee employee2 = new Employee();
            employee2.setId(1002);
            employee2.setEmployeeCode("EMP1002");
            employee2.setFirstName("Mary");
            employee2.setLastName("Johnson");
            employee2.setEmail("mary.johnson@company.com");
            employee2.setPhoneNumber("555-1002");
            employee2.setDesignation("Tech Lead");
            employee2.setSalary(120000);
            employee2.setStatus("ACTIVE");
            employee2.setManager(true);
            employee2.setJoiningDate(new Date());
            employee2.setSkills(Arrays.asList("Java", "Microservices", "AWS"));
            // This is also a simulated blocking I/O operation (reading from FakeDatabase)
            employee2.setDepartment(FakeDatabase.getDepartments().get(1));

            Address address2 = new Address();
            address2.setAddressLine1("200 Oak Avenue");
            address2.setCity("Cary");
            address2.setState("NC");
            address2.setCountry("USA");
            address2.setZipCode("27513");
            employee2.setAddress(address2);

            employees.add(employee1);
            employees.add(employee2);

            // This is the simulated blocking I/O operation being wrapped
            FakeDatabase.initializeEmployees(employees);
        }, initializerExecutor); // Use the dedicated executor
    }
}
