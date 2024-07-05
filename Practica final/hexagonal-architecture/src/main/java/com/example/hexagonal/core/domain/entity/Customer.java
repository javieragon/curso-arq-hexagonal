package com.example.hexagonal.core.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.util.Objects;

@Entity
@Table(name = "customers")
public class Customer implements java.io.Serializable {
	@Serial
	private static final long serialVersionUID = -7077912444215422540L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Embedded
	private Address address;

	// Constructores, getters y setters
	public Customer() {
	}
	public Customer(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	// Getters y setters

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || this.getClass() != o.getClass())
			return false;
		final Customer customer = (Customer) o;
		return Objects.equals(this.id, customer.id) && Objects.equals(this.name, customer.name)
				&& Objects.equals(this.address, customer.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.address);
	}
}
