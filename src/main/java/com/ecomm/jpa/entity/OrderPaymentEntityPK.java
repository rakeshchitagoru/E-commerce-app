package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the order_payment database table.
 * 
 */
@Embeddable
public class OrderPaymentEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_id")
	private String orderId;

	@Column(name="order_payment_id", insertable=false, updatable=false)
	private String orderPaymentId;

	public OrderPaymentEntityPK() {
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderPaymentId() {
		return this.orderPaymentId;
	}
	public void setOrderPaymentId(String orderPaymentId) {
		this.orderPaymentId = orderPaymentId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderPaymentEntityPK)) {
			return false;
		}
		OrderPaymentEntityPK castOther = (OrderPaymentEntityPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.orderPaymentId.equals(castOther.orderPaymentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.orderPaymentId.hashCode();
		
		return hash;
	}
}