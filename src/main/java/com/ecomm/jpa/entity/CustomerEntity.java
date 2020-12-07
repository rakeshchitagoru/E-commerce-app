package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="CustomerEntity.findAll", query="SELECT c FROM CustomerEntity c")
public class CustomerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cust_id")
	private String custId;

	@Column(name="created_at")
	private Timestamp createdAt;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="last_name")
	private String lastName;

	@Column(name="middle_name")
	private String middleName;

	@Column(name="modified_at")
	private Timestamp modifiedAt;

	@Column(name="phone_number")
	private String phoneNumber;

	//bi-directional many-to-one association to CustomerAddressEntity
	@OneToMany(mappedBy="customer")
	private List<CustomerAddressEntity> customerAddresses;

	//bi-directional many-to-one association to CustomerPaymentEntity
	@OneToMany(mappedBy="customer")
	private List<CustomerPaymentEntity> customerPayments;

	public CustomerEntity() {
	}

	public String getCustId() {
		return this.custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public Timestamp getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<CustomerAddressEntity> getCustomerAddresses() {
		return this.customerAddresses;
	}

	public void setCustomerAddresses(List<CustomerAddressEntity> customerAddresses) {
		this.customerAddresses = customerAddresses;
	}

	public CustomerAddressEntity addCustomerAddress(CustomerAddressEntity customerAddress) {
		getCustomerAddresses().add(customerAddress);
		customerAddress.setCustomer(this);

		return customerAddress;
	}

	public CustomerAddressEntity removeCustomerAddress(CustomerAddressEntity customerAddress) {
		getCustomerAddresses().remove(customerAddress);
		customerAddress.setCustomer(null);

		return customerAddress;
	}

	public List<CustomerPaymentEntity> getCustomerPayments() {
		return this.customerPayments;
	}

	public void setCustomerPayments(List<CustomerPaymentEntity> customerPayments) {
		this.customerPayments = customerPayments;
	}

	public CustomerPaymentEntity addCustomerPayment(CustomerPaymentEntity customerPayment) {
		getCustomerPayments().add(customerPayment);
		customerPayment.setCustomer(this);

		return customerPayment;
	}

	public CustomerPaymentEntity removeCustomerPayment(CustomerPaymentEntity customerPayment) {
		getCustomerPayments().remove(customerPayment);
		customerPayment.setCustomer(null);

		return customerPayment;
	}

}