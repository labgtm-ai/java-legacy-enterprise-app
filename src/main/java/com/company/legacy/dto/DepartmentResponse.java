package com.company.legacy.dto;

import java.io.Serializable;


/**
 * Response DTO for Department information.
 *
 * Legacy JavaBean style DTO used in REST responses.
 */
public class DepartmentResponse implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer id;

    private String name;

    private String location;

    private Boolean active;


    public DepartmentResponse() {

    }


    public DepartmentResponse(Integer id,
                              String name,
                              String location,
                              Boolean active) {

        this.id = id;
        this.name = name;
        this.location = location;
        this.active = active;

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

        if (name != null) {
            this.name = name.trim();
        } else {
            this.name = null;
        }

    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {

        if (location != null) {
            this.location = location.trim();
        } else {
            this.location = null;
        }

    }


    public Boolean getActive() {
        return active;
    }


    public void setActive(Boolean active) {
        this.active = active;
    }


    @Override
    public String toString() {

        StringBuffer buffer = new StringBuffer();

        buffer.append("DepartmentResponse [");

        buffer.append("id=");
        buffer.append(id);

        buffer.append(", name=");
        buffer.append(name);

        buffer.append(", location=");
        buffer.append(location);

        buffer.append(", active=");
        buffer.append(active);

        buffer.append("]");

        return buffer.toString();

    }

}