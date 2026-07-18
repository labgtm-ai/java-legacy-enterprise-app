package com.company.legacy.mapper;

import java.util.ArrayList;
import java.util.Collections; // SRAO: Added for Optional and stream operations
import java.util.List;
import java.util.Objects;    // SRAO: Added for Optional and stream operations
import java.util.Optional;   // SRAO: Added for Optional and stream operations
import java.util.stream.Collectors; // SRAO: Added for Optional and stream operations

import org.springframework.stereotype.Component;

import com.company.legacy.dto.DepartmentResponse;
import com.company.legacy.entity.Department;


/**
 * Legacy mapper class for Department conversion.
 *
 * Converts Department entity objects into DTO objects.
 *
 * This represents the manual mapping approach commonly
 * used in older Java enterprise applications.
 */
@Component
public class DepartmentMapper {


    /**
     * Convert Department entity to response DTO.
     *
     * @param department department entity
     * @return department response
     */
    public DepartmentResponse toResponse(
            Department department) {

        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse
        return Optional.ofNullable(department)
                .map(d -> {
                    DepartmentResponse response = new DepartmentResponse();
                    response.setId(d.getId());
                    response.setName(d.getName());
                    response.setLocation(d.getLocation());
                    response.setActive(d.getActive());
                    return response;
                })
                .orElse(null);
    }




    /**
     * Convert list of departments.
     *
     * Legacy implementation using traditional loop.
     *
     * @param departments department list
     * @return response list
     */
    public List<DepartmentResponse> toResponseList(
            List<Department> departments) {

        // SRAO: Replaced explicit null check and traditional loop with Optional and Stream API
        return Optional.ofNullable(departments)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toResponse)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }




    /**
     * Update Department entity fields from another object.
     *
     * Legacy style update method.
     */
    public void updateEntity(
            Department source,
            Department target) {

        // SRAO: Replaced explicit null checks for source and target with Optional.ofNullable and ifPresent
        Optional.ofNullable(source).ifPresent(s -> {
            Optional.ofNullable(target).ifPresent(t -> {
                // SRAO: Replaced internal null checks with Optional.ofNullable and ifPresent
                Optional.ofNullable(s.getName()).ifPresent(t::setName);
                Optional.ofNullable(s.getLocation()).ifPresent(t::setLocation);
                Optional.ofNullable(s.getDescription()).ifPresent(t::setDescription);
                Optional.ofNullable(s.getActive()).ifPresent(t::setActive);
            });
        });
    }




    /**
     * Creates a shallow copy.
     *
     * Legacy applications commonly used
     * manual cloning methods.
     */
    public Department cloneDepartment(
            Department department) {

        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse
        return Optional.ofNullable(department)
                .map(d -> {
                    Department copy = new Department();
                    copy.setId(d.getId());
                    copy.setName(d.getName());
                    copy.setLocation(d.getLocation());
                    copy.setDescription(d.getDescription());
                    copy.setActive(d.getActive());
                    copy.setCreatedDate(d.getCreatedDate());
                    copy.setLastModifiedDate(d.getLastModifiedDate());
                    return copy;
                })
                .orElse(null);
    }
}