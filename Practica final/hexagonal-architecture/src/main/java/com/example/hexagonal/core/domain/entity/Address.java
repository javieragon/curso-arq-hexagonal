package com.example.hexagonal.core.domain.entity;

import jakarta.persistence.*;

@Embeddable
public class Address {
    private String street; private String city;
    private String zipCode;
    // Constructores, getters y setters
    public Address() {}
    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
// Getters y setters

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}