package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the order_item database table.
 * 
 */
@Embeddable
public class OrderItemEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="order_id")
	private String orderId;

	@Column(name="item_id", insertable=false, updatable=false)
	private String itemId;

	public OrderItemEntityPK() {
	}
	public String getOrderId() {
		return this.orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getItemId() {
		return this.itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrderItemEntityPK)) {
			return false;
		}
		OrderItemEntityPK castOther = (OrderItemEntityPK)other;
		return 
			this.orderId.equals(castOther.orderId)
			&& this.itemId.equals(castOther.itemId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orderId.hashCode();
		hash = hash * prime + this.itemId.hashCode();
		
		return hash;
	}
}