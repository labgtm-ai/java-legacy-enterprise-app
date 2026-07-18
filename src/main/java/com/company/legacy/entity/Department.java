package com.company.legacy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

/**
 * Represents a department within the organization.
 *
 * This class intentionally follows traditional JavaBean conventions
 * commonly found in legacy enterprise applications.
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String location;

    private String description;

    private Boolean active;

    private Date createdDate;

    private Date lastModifiedDate;

    public Department() {

    }

    public Department(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.active = true;
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public Department(Integer id,
                      String name,
                      String location,
                      String description,
                      Boolean active,
                      Date createdDate,
                      Date lastModifiedDate) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.active = active;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // SRAO: Replaced explicit null check with Optional.ofNullable().map().orElse()
        this.name = Optional.ofNullable(name).map(String::trim).orElse(null);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        // SRAO: Replaced explicit null check with Optional.ofNullable().map().orElse()
        this.location = Optional.ofNullable(location).map(String::trim).orElse(null);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        // SRAO: Replaced explicit null check with Optional.ofNullable().map().orElse()
        this.description = Optional.ofNullable(description).map(String::trim).orElse(null);
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * Activate the department.
     */
    public void activate() {
        this.active = true;
        this.lastModifiedDate = new Date();
    }

    /**
     * Deactivate the department.
     */
    public void deactivate() {
        this.active = false;
        this.lastModifiedDate = new Date();
    }

    /**
     * Checks whether the department is active.
     */
    public boolean isActiveDepartment() {
        // SRAO: Replaced explicit null check with Optional.ofNullable().orElse()
        return Optional.ofNullable(active).orElse(false);
    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        // SRAO: Replaced explicit null check with Optional.ofNullable().map().orElse()
        result = prime * result + Optional.ofNullable(id).map(Object::hashCode).orElse(0);

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Department)) {
            return false;
        }

        Department other = (Department) obj;

        // SRAO: Replaced explicit null check with Optional.ofNullable().equals()
        return Optional.ofNullable(id).equals(Optional.ofNullable(other.id));
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Department [");
        builder.append("id=").append(id);
        builder.append(", name=").append(name);
        builder.append(", location=").append(location);
        builder.append(", description=").append(description);
        builder.append(", active=").append(active);
        builder.append(", createdDate=").append(createdDate);
        builder.append(", lastModifiedDate=").append(lastModifiedDate);
        builder.append("]");

        return builder.toString();

    }

}
