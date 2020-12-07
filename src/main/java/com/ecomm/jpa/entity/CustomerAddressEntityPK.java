package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the customer_address database table.
 * 
 */
@Embeddable
public class CustomerAddressEntityPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="address_id")
	private String addressId;

	@Column(name="cust_id", insertable=false, updatable=false)
	private String custId;

	public CustomerAddressEntityPK() {
	}
	public String getAddressId() {
		return this.addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
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
		if (!(other instanceof CustomerAddressEntityPK)) {
			return false;
		}
		CustomerAddressEntityPK castOther = (CustomerAddressEntityPK)other;
		return 
			this.addressId.equals(castOther.addressId)
			&& this.custId.equals(castOther.custId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.addressId.hashCode();
		hash = hash * prime + this.custId.hashCode();
		
		return hash;
	}
}