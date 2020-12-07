package com.ecomm.bo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CustomerAddress implements Serializable {
	private static final long serialVersionUID = 1L;

	private String addressId;

	private String custId;

	@NotBlank
	private String address1;

	private String address2;

	private String address3;

	@NotNull
	private String city;

	@NotNull
	private String country;

	@Pattern(regexp = "^(true|false)$")
	private boolean isDefault;

	@Size(min = 10, max = 10)
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNumber;

	@NotNull
	private String state;

	@NotNull
	@Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
	private String zipcode;

	public CustomerAddress() {

	}

	public CustomerAddress(String addressId, String custId, String address1, String address2, String address3,
			String city, String country, boolean isDefault, String phoneNumber, String state, String zipcode) {
		super();
		this.addressId = addressId;
		this.custId = custId;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.city = city;
		this.country = country;
		this.isDefault = isDefault;
		this.phoneNumber = phoneNumber;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getAddressId() {
		return addressId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

}