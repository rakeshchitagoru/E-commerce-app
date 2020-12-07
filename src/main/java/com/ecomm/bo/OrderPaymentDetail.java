package com.ecomm.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class OrderPaymentDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderId;

	private CustomerPayment customerPayment;

	@NotNull
	private BigDecimal discountPrice;

	@NotNull
	private BigDecimal shippingPrice;

	@NotNull
	private BigDecimal taxPrice;

	@NotNull
	private BigDecimal totalPrice;

	private Customer customer;

	@NotNull
	private String confirmationNo;

	public OrderPaymentDetail() {
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(BigDecimal shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getConfirmationNo() {
		return confirmationNo;
	}

	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}

	public CustomerPayment getCustomerPayment() {
		return customerPayment;
	}

	public void setCustomerPayment(CustomerPayment customerPayment) {
		this.customerPayment = customerPayment;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}