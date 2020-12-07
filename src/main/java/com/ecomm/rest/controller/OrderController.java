package com.ecomm.rest.controller;

import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecomm.bo.Order;
import com.ecomm.bo.OrderDetails;
import com.ecomm.exceptions.ResourceNotFoundException;
import com.ecomm.service.OrderService;

@RestController
@RequestMapping("/api")

public class OrderController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;

	@GetMapping("/order")
	public ResponseEntity<?> getOrders() {
		log.error(getRequestId());
		List<OrderDetails> orderList = orderService.getAllOrders();
		if (!orderList.isEmpty()) {
			log.debug("get all returned for requestId {} count {}", getRequestId(), orderList.size());
			return ResponseEntity.status(HttpStatus.OK).body(orderList);
		} else {
			log.warn("Request failed");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<?> getOrderById(@PathVariable(value = "id") String orderId) {
		log.error(getRequestId());
		OrderDetails order = orderService.getOrder(orderId);
		if (order != null) {
			log.debug("get order returned:" + order.toString());
			return ResponseEntity.status(HttpStatus.OK).body(order);
		} else {
			log.warn("OrderDetails doesn't exist");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/order")
	public ResponseEntity<?> createOrder(@Valid @RequestBody Order order) throws ResourceNotFoundException {
		log.error(getRequestId());
		Order orderRes = orderService.saveOrder(order);
		if (orderRes != null) {
			log.debug("get orders saved:" + orderRes.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(orderRes);
		} else {
			log.warn("Order already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(order);
		}
	}

	@PutMapping("/order")
	public ResponseEntity<?> updateOrder(@Valid @RequestBody Order order) throws ResourceNotFoundException {
		log.error(getRequestId());
		Order orderRes = orderService.updateOrder(order);
		if (orderRes != null) {
			log.debug("get order updated:" + orderRes.toString());
			return ResponseEntity.status(HttpStatus.OK).body(orderRes);
		} else {
			log.warn("Order doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(order);
		}
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<?> deleteOrder(@PathVariable(value = "id") String orderId) {
		log.error(getRequestId());
		boolean isDeleted = orderService.deleteOrder(orderId);
		if (isDeleted) {
			log.debug("get order deleted:" + isDeleted);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.warn("Order doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
