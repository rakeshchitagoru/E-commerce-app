package com.ecomm.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name="item")
@NamedQuery(name="ItemEntity.findAll", query="SELECT i FROM ItemEntity i")
public class ItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_id")
	private String itemId;

	@Column(name="created_at")
	private Timestamp createdAt;

	@Column(name="is_available")
	private byte isAvailable;

	@Column(name="item_name")
	private String itemName;

	@Column(name="modified_at")
	private Timestamp modifiedAt;

	private BigDecimal price;

	//bi-directional many-to-one association to OrderItemEntity
	@OneToMany(mappedBy="item")
	private List<OrderItemEntity> orderItems;

	public ItemEntity() {
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
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

	public List<OrderItemEntity> getOrderItems() {
		return this.orderItems;
	}

	public void setOrderItems(List<OrderItemEntity> orderItems) {
		this.orderItems = orderItems;
	}

	public OrderItemEntity addOrderItem(OrderItemEntity orderItem) {
		getOrderItems().add(orderItem);
		orderItem.setItem(this);

		return orderItem;
	}

	public OrderItemEntity removeOrderItem(OrderItemEntity orderItem) {
		getOrderItems().remove(orderItem);
		orderItem.setItem(null);

		return orderItem;
	}

}