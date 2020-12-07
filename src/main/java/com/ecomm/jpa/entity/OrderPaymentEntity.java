package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * The persistent class for the order_payment database table.
 * 
 */
@Entity
@Table(name = "order_payment")
@NamedQuery(name = "OrderPaymentEntity.findAll", query = "SELECT o FROM OrderPaymentEntity o")
public class OrderPaymentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderPaymentEntityPK id;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@Column(name = "discount_price")
	private BigDecimal discountPrice;

	@Column(name = "modified_at")
	private Timestamp modifiedAt;

	@Column(name = "shipping_price")
	private BigDecimal shippingPrice;

	@Column(name = "tax_price")
	private BigDecimal taxPrice;

	@Column(name = "total_price")
	private BigDecimal totalPrice;

	@Column(name = "confirmation_no")
	private String confirmationNo;
	
	@Column(name = "cust_id")
	private String custId;

	// bi-directional many-to-one association to CustomerPaymentEntity
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "cust_id", referencedColumnName = "cust_id", insertable=false, updatable=false),
			@JoinColumn(name = "order_payment_id", referencedColumnName = "payment_id", insertable=false, updatable=false) })
	private CustomerPaymentEntity customerPayment;

	public OrderPaymentEntity() {
	}

	public OrderPaymentEntityPK getId() {
		return this.id;
	}

	public void setId(OrderPaymentEntityPK id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public BigDecimal getDiscountPrice() {
		return this.discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Timestamp getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public BigDecimal getShippingPrice() {
		return this.shippingPrice;
	}

	public void setShippingPrice(BigDecimal shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public BigDecimal getTaxPrice() {
		return this.taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public CustomerPaymentEntity getCustomerPayment() {
		return this.customerPayment;
	}

	public void setCustomerPayment(CustomerPaymentEntity customerPayment) {
		this.customerPayment = customerPayment;
	}

	public String getConfirmationNo() {
		return confirmationNo;
	}

	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

}