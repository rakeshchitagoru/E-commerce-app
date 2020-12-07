package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the customer_payment database table.
 * 
 */
@Entity
@Table(name="customer_payment")
@NamedQuery(name="CustomerPaymentEntity.findAll", query="SELECT c FROM CustomerPaymentEntity c")
public class CustomerPaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CustomerPaymentEntityPK id;

	private String address;

	@Column(name="card_no")
	private String cardNo;

	private String city;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="is_active")
	private byte isActive;

	@Column(name="is_default")
	private byte isDefault;

	@Column(name="modified_at")
	private Timestamp modifiedAt;

	@Column(name="payment_type")
	private String paymentType;

	private String state;

	private String zipcode;

	//bi-directional many-to-one association to CustomerEntity
	@ManyToOne
	@JoinColumn(name="cust_id",insertable=false, updatable=false)
	private CustomerEntity customer;

	//bi-directional many-to-one association to OrderPaymentEntity
	@OneToMany(mappedBy="customerPayment")
	private List<OrderPaymentEntity> orderPayments;

	public CustomerPaymentEntity() {
	}

	public CustomerPaymentEntityPK getId() {
		return this.id;
	}

	public void setId(CustomerPaymentEntityPK id) {
		this.id = id;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCardNo() {
		return this.cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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

	public List<OrderPaymentEntity> getOrderPayments() {
		return this.orderPayments;
	}

	public void setOrderPayments(List<OrderPaymentEntity> orderPayments) {
		this.orderPayments = orderPayments;
	}

	public OrderPaymentEntity addOrderPayment(OrderPaymentEntity orderPayment) {
		getOrderPayments().add(orderPayment);
		orderPayment.setCustomerPayment(this);

		return orderPayment;
	}

	public OrderPaymentEntity removeOrderPayment(OrderPaymentEntity orderPayment) {
		getOrderPayments().remove(orderPayment);
		orderPayment.setCustomerPayment(null);

		return orderPayment;
	}

}