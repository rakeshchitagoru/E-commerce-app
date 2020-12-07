package com.ecomm.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	private String orderId;

	private String itemId;

	@NotNull
	private BigDecimal price;

	@NotNull
	private int quantity;

	@NotNull
	private String shippingType;

	@NotNull
	private String status;

	private String custId;

	private String addressId;

	public OrderItem(String orderId, String itemId, BigDecimal price, int quantity, String shippingType, String status,
			String custId, String addressId) {
		super();
		this.orderId = orderId;
		this.itemId = itemId;
		this.price = price;
		this.quantity = quantity;
		this.shippingType = shippingType;
		this.status = status;
		this.custId = custId;
		this.addressId = addressId;
	}

	public OrderItem() {

	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

}