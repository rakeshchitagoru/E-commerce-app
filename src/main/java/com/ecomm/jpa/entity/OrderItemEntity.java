package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * The persistent class for the order_item database table.
 * 
 */
@Entity
@Table(name="order_item")
@NamedQuery(name="OrderItemEntity.findAll", query="SELECT o FROM OrderItemEntity o")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderItemEntityPK id;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="modified_at")
	private Timestamp modifiedAt;

	private BigDecimal price;

	private int quantity;

	@Column(name="shipping_type")
	private String shippingType;

	private String status;

	//bi-directional many-to-one association to CustomerAddressEntity
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="address_id", referencedColumnName="address_id"),
		@JoinColumn(name="cust_id", referencedColumnName="cust_id")
		})
	private CustomerAddressEntity customerAddress;

	//bi-directional many-to-one association to ItemEntity
	@ManyToOne
	@JoinColumn(name="item_id", insertable=false, updatable=false)
	private ItemEntity item;

	public OrderItemEntity() {
	}

	public OrderItemEntityPK getId() {
		return this.id;
	}

	public void setId(OrderItemEntityPK id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getModifiedAt() {
		return this.modifiedAt;
	}

	public void setModifiedAt(Timestamp modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getShippingType() {
		return this.shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CustomerAddressEntity getCustomerAddress() {
		return this.customerAddress;
	}

	public void setCustomerAddress(CustomerAddressEntity customerAddress) {
		this.customerAddress = customerAddress;
	}

	public ItemEntity getItem() {
		return this.item;
	}

	public void setItem(ItemEntity item) {
		this.item = item;
	}

}