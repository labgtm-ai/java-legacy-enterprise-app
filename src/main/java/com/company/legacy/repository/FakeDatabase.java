package com.company.legacy.repository;

import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import com.company.legacy.entity.Department;
import com.company.legacy.entity.Employee;

/**
 * Simulates a legacy in-memory database.
 *
 * This class intentionally uses older Java collection classes like
 * Vector and Hashtable to provide modernization opportunities.
 */
public final class FakeDatabase {

    private static final Vector<Employee> EMPLOYEES =
            new Vector<Employee>();

    private static final Hashtable<Integer, Department> DEPARTMENTS =
            new Hashtable<Integer, Department>();

    static {

        initializeDepartments();
        initializeEmployees();

    }

    private FakeDatabase() {

    }

    private static void initializeDepartments() {

        Department it = new Department();
        it.setId(1);
        it.setName("IT");
        it.setLocation("New York");

        Department hr = new Department();
        hr.setId(2);
        hr.setName("HR");
        hr.setLocation("Chicago");

        Department finance = new Department();
        finance.setId(3);
        finance.setName("Finance");
        finance.setLocation("Dallas");

        DEPARTMENTS.put(it.getId(), it);
        DEPARTMENTS.put(hr.getId(), hr);
        DEPARTMENTS.put(finance.getId(), finance);

    }

    public static void initializeEmployees() {

        Employee emp1 = new Employee();
        emp1.setId(1001);
        emp1.setFirstName("John");
        emp1.setLastName("Smith");
        emp1.setEmail("john.smith@company.com");
        emp1.setSalary(95000.00);
        emp1.setDepartment(DEPARTMENTS.get(1));
        emp1.setJoiningDate(new Date());

        Employee emp2 = new Employee();
        emp2.setId(1002);
        emp2.setFirstName("Mary");
        emp2.setLastName("Johnson");
        emp2.setEmail("mary.johnson@company.com");
        emp2.setSalary(87000.00);
        emp2.setDepartment(DEPARTMENTS.get(2));
        emp2.setJoiningDate(new Date());

        Employee emp3 = new Employee();
        emp3.setId(1003);
        emp3.setFirstName("David");
        emp3.setLastName("Wilson");
        emp3.setEmail("david.wilson@company.com");
        emp3.setSalary(120000.00);
        emp3.setDepartment(DEPARTMENTS.get(1));
        emp3.setJoiningDate(new Date());

        EMPLOYEES.add(emp1);
        EMPLOYEES.add(emp2);
        EMPLOYEES.add(emp3);

    }

    public static synchronized Vector<Employee> getEmployees() {

        return EMPLOYEES;

    }

    public static synchronized Hashtable<Integer, Department> getDepartments() {

        return DEPARTMENTS;

    }

    private static Vector<Employee> employees =
            new Vector<Employee>();


    private static Hashtable<Integer, Department> departments =
            new Hashtable<Integer, Department>();




    /**
     * Initialize employees.
     *
     * Called by DataInitializer during startup.
     */
    public static void initializeEmployees(
            List<Employee> employeeList) {


        employees.clear();



        if(employeeList != null) { // SRAO: Replaced traditional for-loop with Stream API for collection population.
            employeeList.stream().forEach(employees::add);
        }


    }





    /**
     * Initialize departments.
     *
     * Called by DataInitializer during startup.
     */
    public static void initializeDepartments(
            List<Department> departmentList) {


        departments.clear();



        if(departmentList != null) { // SRAO: Replaced traditional for-loop with Stream API for map population.
            departmentList.stream().forEach(department ->
                departments.put(department.getId(), department));
        }


    }








}
