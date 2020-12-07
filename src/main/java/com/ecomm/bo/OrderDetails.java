package com.ecomm.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderId;
	private List<OrderItemDetail> orderItemList;
	private List<OrderPaymentDetail> orderPaymentList;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public List<OrderItemDetail> getOrderItemList() {
		return orderItemList;
	}

	public void setOrderItemList(List<OrderItemDetail> orderItemList) {
		this.orderItemList = orderItemList;
	}

	public List<OrderPaymentDetail> getOrderPaymentList() {
		return orderPaymentList;
	}

	public void setOrderPaymentList(List<OrderPaymentDetail> orderPaymentList) {
		this.orderPaymentList = orderPaymentList;
	}

	public OrderDetails() {
		orderItemList = new ArrayList<>();
		orderPaymentList = new ArrayList<>();
	}
	
	public OrderDetails(String orderId) {
		this.orderId = orderId;
		orderItemList = new ArrayList<>();
		orderPaymentList = new ArrayList<>();
	}

	public void addOrderItem(OrderItemDetail oi) {
		orderItemList.add(oi);
	}

	public void addOrderPayment(OrderPaymentDetail op) {
		orderPaymentList.add(op);
	}

	@Override
	public String toString() {
		return "OrderDetails [orderId=" + orderId + ", orderItemList=" + orderItemList + ", orderPaymentList="
				+ orderPaymentList + "]";
	}
	
	

}
