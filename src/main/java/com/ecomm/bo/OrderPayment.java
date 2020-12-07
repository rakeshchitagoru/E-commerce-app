package com.ecomm.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class OrderPayment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderId;

	@NotNull
	private String orderPaymentId;

	@NotNull
	private BigDecimal discountPrice;

	@NotNull
	private BigDecimal shippingPrice;

	@NotNull
	private BigDecimal taxPrice;

	@NotNull
	private BigDecimal totalPrice;

	private String custId;

	@NotNull
	private String confirmationNo;

	public OrderPayment() {
	}

	public OrderPayment(String orderId, String orderPaymentId, BigDecimal discountPrice, BigDecimal shippingPrice,
			BigDecimal taxPrice, BigDecimal totalPrice, String custId, String confirmationNo) {
		super();
		this.orderId = orderId;
		this.orderPaymentId = orderPaymentId;
		this.discountPrice = discountPrice;
		this.shippingPrice = shippingPrice;
		this.taxPrice = taxPrice;
		this.totalPrice = totalPrice;
		this.custId = custId;
		this.confirmationNo = confirmationNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderPaymentId() {
		return orderPaymentId;
	}

	public void setOrderPaymentId(String orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
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

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getConfirmationNo() {
		return confirmationNo;
	}

	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}

}