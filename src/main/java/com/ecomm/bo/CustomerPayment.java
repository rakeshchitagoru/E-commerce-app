package com.ecomm.bo;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String paymentId;
	private String custId;

	@NotNull
	private String address;

	@NotNull
	private String cardNo;

	@NotNull
	private String city;

	@Pattern(regexp = "^(true|false)$")
	private boolean isDefault;

	private boolean isActive;

	@NotNull
	private String paymentType;

	@NotNull
	private String state;

	@NotNull
	@Pattern(regexp = "^[0-9]{5}(?:-[0-9]{4})?$")
	private String zipcode;

	public CustomerPayment() {

	}

	public CustomerPayment(String paymentId, String custId, String address, String cardNo, String city,
			boolean isDefault, String paymentType, String state, String zipcode) {
		super();
		this.paymentId = paymentId;
		this.custId = custId;
		this.address = address;
		this.cardNo = cardNo;
		this.city = city;
		this.isDefault = isDefault;
		this.paymentType = paymentType;
		this.state = state;
		this.zipcode = zipcode;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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