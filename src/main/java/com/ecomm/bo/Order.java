package com.ecomm.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderId;
	private List<OrderItem> orderItemList;
	private List<OrderPayment> orderPaymentList;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderItem> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItem> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public List<OrderPayment> getOrderPaymentList() {
		return orderPaymentList;
	}

	public void setOrderPaymentList(List<OrderPayment> orderPaymentList) {
		this.orderPaymentList = orderPaymentList;
	}

	public Order() {
		orderItemList = new ArrayList<>();
		orderPaymentList = new ArrayList<>();
	}
	
	public Order(String orderId) {
		this.orderId = orderId;
		orderItemList = new ArrayList<>();
		orderPaymentList = new ArrayList<>();
	}

	public void addOrderItem(OrderItem oi) {
		orderItemList.add(oi);
	}

	public void addOrderPayment(OrderPayment op) {
		orderPaymentList.add(op);
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderItemList=" + orderItemList + ", orderPaymentList="
				+ orderPaymentList + "]";
	}
	
	

}
