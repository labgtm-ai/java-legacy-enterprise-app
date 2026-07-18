package com.company.legacy.constants;

import java.time.format.DateTimeFormatter; // SRAO: Added import for java.time.format.DateTimeFormatter

/**
 * Legacy application constants.
 *
 * NOTE:
 * This interface-based constants pattern was commonly used in
 * older Java applications. Modern applications should prefer
 * a final utility class with a private constructor.
 */
public interface ApplicationConstants {

    // Application Information
    String APPLICATION_NAME = "Legacy Employee Management Service";
    String APPLICATION_VERSION = "1.0";

    // Employee Status
    String STATUS_ACTIVE = "ACTIVE";
    String STATUS_INACTIVE = "INACTIVE";
    String STATUS_TERMINATED = "TERMINATED";

    // Departments
    String DEPARTMENT_IT = "IT";
    String DEPARTMENT_HR = "HR";
    String DEPARTMENT_FINANCE = "FINANCE";
    String DEPARTMENT_ADMIN = "ADMIN";

    // Messages
    String EMPLOYEE_NOT_FOUND = "Employee not found.";
    String EMPLOYEE_CREATED = "Employee created successfully.";
    String EMPLOYEE_UPDATED = "Employee updated successfully.";
    String EMPLOYEE_DELETED = "Employee deleted successfully.";

    // Date Format
    // SRAO: Replaced String date format with a DateTimeFormatter instance for modern date handling.
    DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Report
    String REPORT_FILE_NAME = "employee-report.csv";

    // Thread
    int SYNC_INTERVAL_SECONDS = 30;

    // Pagination
    int DEFAULT_PAGE_SIZE = 10;
    int MAX_PAGE_SIZE = 100;

}
