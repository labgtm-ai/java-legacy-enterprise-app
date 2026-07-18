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
        // SRAO: Replaced explicit null check with Optional.ofNullable for cleaner assignment
        this.addressLine1 = Optional.ofNullable(addressLine1).map(String::trim).orElse(null);
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        // SRAO: Replaced explicit null check with Optional.ofNullable for cleaner assignment
        this.addressLine2 = Optional.ofNullable(addressLine2).map(String::trim).orElse(null);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        // SRAO: Replaced explicit null check with Optional.ofNullable for cleaner assignment
        this.city = Optional.ofNullable(city).map(String::trim).orElse(null);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        // SRAO: Replaced explicit null check with Optional.ofNullable for cleaner assignment
        this.state = Optional.ofNullable(state).map(String::trim).orElse(null);
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        // SRAO: Replaced explicit null check with Optional.ofNullable for cleaner assignment
        this.country = Optional.ofNullable(country).map(String::trim).orElse(null);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        // SRAO: Replaced explicit null check with Optional.ofNullable for cleaner assignment
        this.zipCode = Optional.ofNullable(zipCode).map(String::trim).orElse(null);
    }

    /**
     * Returns the full address as a single string.
     */
    public String getFullAddress() {

        StringBuilder buffer = new StringBuilder(); // SRAO: Replaced StringBuffer with StringBuilder for performance.

        // SRAO: Replaced explicit null check with Optional.ofNullable and ifPresent
        Optional.ofNullable(addressLine1).ifPresent(buffer::append);

        // SRAO: Replaced explicit null check with Optional.ofNullable, filter, and ifPresent
        Optional.ofNullable(addressLine2)
                .filter(s -> !s.isEmpty())
                .ifPresent(s -> {
                    buffer.append(", ");
                    buffer.append(s);
                });

        // SRAO: Replaced explicit null check with Optional.ofNullable and ifPresent
        Optional.ofNullable(city)
                .ifPresent(s -> {
                    buffer.append(", ");
                    buffer.append(s);
                });

        // SRAO: Replaced explicit null check with Optional.ofNullable and ifPresent
        Optional.ofNullable(state)
                .ifPresent(s -> {
                    buffer.append(", ");
                    buffer.append(s);
                });

        // SRAO: Replaced explicit null check with Optional.ofNullable and ifPresent
        Optional.ofNullable(country)
                .ifPresent(s -> {
                    buffer.append(", ");
                    buffer.append(s);
                });

        // SRAO: Replaced explicit null check with Optional.ofNullable and ifPresent
        Optional.ofNullable(zipCode)
                .ifPresent(s -> {
                    buffer.append(" - ");
                    buffer.append(s);
                });

        return buffer.toString();

    }

    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;

        // SRAO: Retained original null check as Optional is not idiomatic for hashCode
        result = prime * result + ((id == null) ? 0 : id.hashCode());

        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        // SRAO: Retained original null check as Optional is not idiomatic for equals
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof Address)) {
            return false;
        }

        Address other = (Address) obj;

        // SRAO: Retained original null check as Optional is not idiomatic for equals
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

        StringBuilder buffer = new StringBuilder(); // SRAO: Replaced StringBuffer with StringBuilder for performance.

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
