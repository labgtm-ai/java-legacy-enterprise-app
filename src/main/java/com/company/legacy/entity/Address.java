package com.company.legacy.entity;

import java.io.Serializable;
import java.util.Optional;

/**
 * Represents an employee's address.
 *
 * This class intentionally follows traditional JavaBean conventions
 * commonly found in legacy Java applications.
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    public Address() {

    }

    public Address(Integer id,
                   String addressLine1,
                   String city,
                   String state,
                   String country,
                   String zipCode) {

        this.id = id;
        this.addressLine1 = addressLine1;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipCode = zipCode;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse.
        this.addressLine1 = Optional.ofNullable(addressLine1)
                                .map(String::trim)
                                .orElse(null);
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse.
        this.addressLine2 = Optional.ofNullable(addressLine2)
                                .map(String::trim)
                                .orElse(null);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse.
        this.city = Optional.ofNullable(city)
                                .map(String::trim)
                                .orElse(null);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse.
        this.state = Optional.ofNullable(state)
                                .map(String::trim)
                                .orElse(null);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse.
        this.country = Optional.ofNullable(country)
                                .map(String::trim)
                                .orElse(null);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        // SRAO: Replaced explicit null check with Optional.ofNullable and map/orElse.
        this.zipCode = Optional.ofNullable(zipCode)
                                .map(String::trim)
                                .orElse(null);
    }

    /**
     * Returns the full address as a single string.
     */
    public String getFullAddress() {

        // SRAO: Replaced StringBuffer with StringBuilder for better performance in a non-thread-safe context.
        StringBuilder buffer = new StringBuilder();

        if (addressLine1 != null) {
            buffer.append(addressLine1);
        }

        if (addressLine2 != null && addressLine2.length() > 0) {
            buffer.append(", ");
            buffer.append(addressLine2);
        }

        if (city != null) {
            buffer.append(", ");
            buffer.append(city);
        }

        if (state != null) {
            buffer.append(", ");
            buffer.append(state);
        }

        if (country != null) {
            buffer.append(", ");
            buffer.append(country);
        }

        if (zipCode != null) {
            buffer.append(" - ");
            buffer.append(zipCode);
        }

        return buffer.toString();

    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        result = prime * result + ((id == null) ? 0 : id.hashCode());

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

        if (!(obj instanceof Address)) {
            return false;
        }

        Address other = (Address) obj;

        if (id == null) {

            if (other.id != null) {
                return false;
            }

        } else if (!id.equals(other.id)) {

            return false;

        }

        return true;

    }

    @Override
    public String toString() {

        // SRAO: Replaced StringBuffer with StringBuilder for better performance in a non-thread-safe context.
        StringBuilder buffer = new StringBuilder();

        buffer.append("Address [");
        buffer.append("id=").append(id);
        buffer.append(", addressLine1=").append(addressLine1);
        buffer.append(", addressLine2=").append(addressLine2);
        buffer.append(", city=").append(city);
        buffer.append(", state=").append(state);
        buffer.append(", country=").append(country);
        buffer.append(", zipCode=").append(zipCode);
        buffer.append("]");

        return buffer.toString();

    }

}
