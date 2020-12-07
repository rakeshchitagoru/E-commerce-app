package com.ecomm.bo;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String custId;

	@NotNull
	@Email
	private String email;

	@NotNull
	@Size(min = 3, max = 50)
	private String firstName;

	@NotNull
	@Size(min = 3, max = 50)
	private String lastName;

	private String middleName;

	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNumber;

	private byte isActive;

	public Customer(String custId, String firstName, String middleName, String lastName, String email,
			String phoneNumber, byte isActive) {
		super();
		this.custId = custId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
	}

	public Customer() {
		super();
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public byte getIsActive() {
		return isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", middleName=" + middleName + ", phoneNumber=" + phoneNumber + ", isActive=" + isActive + "]";
	}

}