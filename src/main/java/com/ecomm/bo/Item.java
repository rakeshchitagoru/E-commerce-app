package com.ecomm.bo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	private String itemId;

	private byte isAvailable;

	@NotNull
	private String itemName;

	private BigDecimal price;

	public Item() {
	}

	public Item(String itemId, byte isAvailable, String itemName, BigDecimal price) {
		super();
		this.itemId = itemId;
		this.isAvailable = isAvailable;
		this.itemName = itemName;
		this.price = price;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public byte getIsAvailable() {
		return this.isAvailable;
	}

	public void setIsAvailable(byte isAvailable) {
		this.isAvailable = isAvailable;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", isAvailable=" + isAvailable + ", itemName=" + itemName + ", price=" + price
				+ "]";
	}

}