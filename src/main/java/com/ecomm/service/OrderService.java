package com.ecomm.service;

import java.util.List;

import com.ecomm.bo.Order;
import com.ecomm.bo.OrderDetails;
import com.ecomm.exceptions.ResourceNotFoundException;

public interface OrderService {
	public List<OrderDetails> getAllOrders();

	public OrderDetails getOrder(String orderId);

	public Order saveOrder(Order order) throws ResourceNotFoundException;

	public Order updateOrder(Order order) throws ResourceNotFoundException;

	public boolean deleteOrder(String orderId);
}
