package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the customer_address database table.
 * 
 */
@Entity
@Table(name="customer_address")
@NamedQuery(name="CustomerAddressEntity.findAll", query="SELECT c FROM CustomerAddressEntity c")
public class CustomerAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CustomerAddressEntityPK id;

	private String address1;

	private String address2;

	private String address3;

	private String city;

	private String country;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="is_default")
	private byte isDefault;

	@Column(name="modified_at")
	private Timestamp modifiedAt;

	@Column(name="phone_number")
	private String phoneNumber;

	private String state;

	private String zipcode;

	//bi-directional many-to-one association to CustomerEntity
	@ManyToOne
	@JoinColumn(name="cust_id", insertable=false, updatable=false)
	private CustomerEntity customer;

	//bi-directional many-to-one association to OrderItemEntity
	@OneToMany(mappedBy="customerAddress")
	private List<OrderItemEntity> orderItems;

	public CustomerAddressEntity() {
	}

	public CustomerAddressEntityPK getId() {
		return this.id;
	}

	public void setId(CustomerAddressEntityPK id) {
		this.id = id;
	}

	public String getAddress1() {
		return this.address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return this.address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public byte getIsActive() {
		return this.isActive;
	}

	public void setIsActive(byte isActive) {
		this.isActive = isActive;
	}

	public byte getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(byte isDefault) {
		this.isDefault = isDefault;
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

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public CustomerEntity getCustomer() {
		return this.customer;
	}

	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}

	public List<OrderItemEntity> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItemEntity addOrderItem(OrderItemEntity orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setCustomerAddress(this);

		return orderItem;
	}

	public OrderItemEntity removeOrderItem(OrderItemEntity orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setCustomerAddress(null);

		return orderItem;
	}

}