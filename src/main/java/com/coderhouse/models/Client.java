package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;

//import java.util.ArrayList;

//import java.util.List;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

//import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="Clients")
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private long clientId;
	
	
	@Column (name = "dni", nullable=false, unique=true)
	private String dni;
	
	@Column (name = "first_name")
	private String firstName;
	
	@Column (name="last_name")
	private String lastName;
	
	@Column (name="address_street")
	private String addressStreet;
	
	@Column (name="postal_code")
	private String postalCode;
	
	@Column (name="phone_number")
	private String phoneNumber;
	
	
	@OneToMany(mappedBy="client", cascade=CascadeType.ALL)
    @JsonManagedReference 
	private List<Invoice> invoices = new ArrayList<>();

	public long getClientId() {
		return clientId;
	}

	public void setClientId(long clientId) {
		this.clientId = clientId;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
/*
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}
*/
	
	
	public Client() {
        // Hibernate necesita este constructor vacío para instanciar el objeto
    }

	public Client(String dni, String firstName, String lastName, String addressStreet, String postalCode,
			String phoneNumber) {
		super();
		this.dni = dni;
		this.firstName = firstName;
		this.lastName = lastName;
		this.addressStreet = addressStreet;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
	}

	
	

	
	
}
