package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the customer_payment database table.
 * 
 */
@Embeddable
public class CustomerPaymentEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="payment_id")
	private String paymentId;

	@Column(name="cust_id", insertable=false, updatable=false)
	private String custId;

	public CustomerPaymentEntityPK() {
	}
	public String getPaymentId() {
		return this.paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getCustId() {
		return this.custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CustomerPaymentEntityPK)) {
			return false;
		}
		CustomerPaymentEntityPK castOther = (CustomerPaymentEntityPK)other;
		return 
			this.paymentId.equals(castOther.paymentId)
			&& this.custId.equals(castOther.custId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.paymentId.hashCode();
		hash = hash * prime + this.custId.hashCode();
		
		return hash;
	}
}