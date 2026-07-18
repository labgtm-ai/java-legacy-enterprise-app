package com.company.legacy.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

import com.company.legacy.entity.Department;
import com.company.legacy.entity.Employee;

/**
 * Simulates a legacy in-memory database.
 *
 * This class intentionally uses older Java collection classes like
 * Vector and Hashtable to provide modernization opportunities.
 */
public final class FakeDatabase {

    // Consolidated to a single set of static, thread-safe, mutable collections.
    // Collections.synchronizedList is used for thread-safe list operations.
    private static final List<Employee> employees = Collections.synchronizedList(new ArrayList<>());

    // ConcurrentHashMap is used for thread-safe map operations.
    private static final ConcurrentHashMap<Integer, Department> departments = new ConcurrentHashMap<>();

    static {
        // Initialize with default data upon class loading
        initializeDefaultDepartments();
        initializeDefaultEmployees();
    }

    private FakeDatabase() {
        // Private constructor to prevent instantiation
    }

    /**
     * Initializes the database with default department data.
     * This method is called once during static initialization.
     */
    private static void initializeDefaultDepartments() {
        List<Department> defaultDepartments = new ArrayList<>();

        Department it = new Department();
        it.setId(1);
        it.setName("IT");
        it.setLocation("New York");
        defaultDepartments.add(it);

        Department hr = new Department();
        hr.setId(2);
        hr.setName("HR");
        hr.setLocation("Chicago");
        defaultDepartments.add(hr);

        Department finance = new Department();
        finance.setId(3);
        finance.setName("Finance");
        finance.setLocation("Dallas");
        defaultDepartments.add(finance);

        // Use the public initializer to set the default data
        initializeDepartments(defaultDepartments);
    }

    /**
     * Initializes the database with default employee data.
     * This method is called once during static initialization.
     */
    private static void initializeDefaultEmployees() {
        List<Employee> defaultEmployees = new ArrayList<>();

        // Departments must be initialized first to be available for employees
        Map<Integer, Department> currentDepartments = getDepartments();

        Employee emp1 = new Employee();
        emp1.setId(1001);
        emp1.setFirstName("John");
        emp1.setLastName("Smith");
        emp1.setEmail("john.smith@company.com");
        emp1.setSalary(95000.00);
        emp1.setDepartment(currentDepartments.get(1));
        emp1.setJoiningDate(new Date());
        defaultEmployees.add(emp1);

        Employee emp2 = new Employee();
        emp2.setId(1002);
        emp2.setFirstName("Mary");
        emp2.setLastName("Johnson");
        emp2.setEmail("mary.johnson@company.com");
        emp2.setSalary(87000.00);
        emp2.setDepartment(currentDepartments.get(2));
        emp2.setJoiningDate(new Date());
        defaultEmployees.add(emp2);

        Employee emp3 = new Employee();
        emp3.setId(1003);
        emp3.setFirstName("David");
        emp3.setLastName("Wilson");
        emp3.setEmail("david.wilson@company.com");
        emp3.setSalary(120000.00);
        emp3.setDepartment(currentDepartments.get(1));
        emp3.setJoiningDate(new Date());
        defaultEmployees.add(emp3);

        // Use the public initializer to set the default data
        initializeEmployees(defaultEmployees);
    }

    /**
     * Returns a thread-safe list of all employees.
     * The returned list is the actual backing store, so modifications
     * to it will affect the database.
     * @return A List of Employee objects.
     */
    public static synchronized List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Returns a thread-safe map of all departments, keyed by ID.
     * The returned map is the actual backing store, so modifications
     * to it will affect the database.
     * @return A ConcurrentHashMap of Department objects.
     */
    public static synchronized ConcurrentHashMap<Integer, Department> getDepartments() {
        return departments;
    }

    /**
     * Initializes employees.
     *
     * Called by DataInitializer during startup.
     * Replaces all existing employee data with the provided list.
     */
    public static void initializeEmployees(List<Employee> employeeList) {
        employees.clear();
        if (employeeList != null) {
            employees.addAll(employeeList);
        }
    }

    /**
     * Initializes departments.
     *
     * Called by DataInitializer during startup.
     * Replaces all existing department data with the provided list.
     */
    public static void initializeDepartments(List<Department> departmentList) {
        departments.clear();
        if (departmentList != null) {
            departmentList.forEach(department -> departments.put(department.getId(), department));
        }
    }
}
