package com.ecomm.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.bo.Order;
import com.ecomm.bo.OrderDetails;
import com.ecomm.bo.OrderItem;
import com.ecomm.bo.OrderItemDetail;
import com.ecomm.bo.OrderPayment;
import com.ecomm.bo.OrderPaymentDetail;
import com.ecomm.exceptions.ResourceNotFoundException;
import com.ecomm.jpa.entity.CustomerAddressEntity;
import com.ecomm.jpa.entity.CustomerAddressEntityPK;
import com.ecomm.jpa.entity.CustomerPaymentEntity;
import com.ecomm.jpa.entity.CustomerPaymentEntityPK;
import com.ecomm.jpa.entity.OrderItemEntity;
import com.ecomm.jpa.entity.OrderPaymentEntity;
import com.ecomm.jpa.repository.CustomerAddressRepository;
import com.ecomm.jpa.repository.CustomerPaymentRepository;
import com.ecomm.jpa.repository.OrderItemRepository;
import com.ecomm.jpa.repository.OrderPaymentRepository;
import com.ecomm.util.OrderUtil;
import com.ecomm.util.UUIDUtil;

@Service
public class OrderServiceImpl implements OrderService {
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private OrderPaymentRepository orderPaymentRepository;
	@Autowired
	private CustomerAddressRepository customerAddressRepository;
	@Autowired
	private CustomerPaymentRepository customerPaymentRepository;

	@Override
	public List<OrderDetails> getAllOrders() {

		List<OrderItemDetail> listofOrderItems = new ArrayList<>();
		for (OrderItemEntity oiEnt : orderItemRepository.findAll()) {
			listofOrderItems.add(OrderUtil.toOrderItemBo(oiEnt));
		}
		log.debug("get all returned size {}", listofOrderItems.size());

		List<OrderPaymentDetail> listofOrderPayments = new ArrayList<>();
		for (OrderPaymentEntity opEnt : orderPaymentRepository.findAll()) {
			listofOrderPayments.add(OrderUtil.toOrderPaymentBo(opEnt));
		}
		log.debug("get all returned size {}", listofOrderPayments.size());
		return OrderUtil.prepareOrders(listofOrderItems, listofOrderPayments);
	}

	@Override
	public OrderDetails getOrder(String orderId) {

		List<OrderItemDetail> listofOrderItems = new ArrayList<>();
		for (OrderItemEntity oiEnt : orderItemRepository.findByIdOrderId(orderId)) {
			listofOrderItems.add(OrderUtil.toOrderItemBo(oiEnt));
		}
		log.debug("get all returned size {}", listofOrderItems.size());

		List<OrderPaymentDetail> listofOrderPayments = new ArrayList<>();
		for (OrderPaymentEntity opEnt : orderPaymentRepository.findByIdOrderId(orderId)) {
			listofOrderPayments.add(OrderUtil.toOrderPaymentBo(opEnt));
		}
		log.debug("get all returned size {}", listofOrderPayments.size());

		List<OrderDetails> orderList = OrderUtil.prepareOrders(listofOrderItems, listofOrderPayments);
		return orderList.isEmpty() ? null : orderList.get(0);
	}

	@Override
	@Transactional
	public Order saveOrder(Order order) throws ResourceNotFoundException {

		if (order.getOrderId() == null) {
			order.setOrderId(UUIDUtil.getUUID());
		} else {
			if (!orderItemRepository.findByIdOrderId(order.getOrderId()).isEmpty()) {
				return null;
			}
		}

		for (OrderItem oi : order.getOrderItemList()) {
			log.debug("get orderitem saved:" + oi.toString());
			saveOrUpdateOrderItem(order.getOrderId(), oi);
		}

		for (OrderPayment op : order.getOrderPaymentList()) {
			log.debug("get orderpayment saved:" + op.toString());
			saveOrUpdateOrderPayment(order.getOrderId(), op);
		}

		return order;
	}

	private void saveOrUpdateOrderPayment(String orderId, OrderPayment op) throws ResourceNotFoundException {
		if (op.getOrderId() == null) {
			op.setOrderId(orderId);
		}

		CustomerPaymentEntityPK pk = new CustomerPaymentEntityPK();
		pk.setPaymentId(op.getOrderPaymentId());
		pk.setCustId(op.getCustId());
		Optional<CustomerPaymentEntity> entity = customerPaymentRepository.findById(pk);
		if (entity.isPresent()) {
			log.debug("get paymententity saved/updated:" + entity.toString());
			orderPaymentRepository.save(OrderUtil.toOrderPaymentEntity(op, entity.get(), false));
		} else {
			throw new ResourceNotFoundException("Customer address not exist");
		}
	}

	private void saveOrUpdateOrderItem(String orderId, OrderItem oi) throws ResourceNotFoundException {
		if (oi.getOrderId() == null) {
			oi.setOrderId(orderId);
		}

		CustomerAddressEntityPK pk = new CustomerAddressEntityPK();
		pk.setAddressId(oi.getAddressId());
		pk.setCustId(oi.getCustId());
		Optional<CustomerAddressEntity> entity = customerAddressRepository.findById(pk);
		if (entity.isPresent()) {
			log.debug("get itementity saved/updated:" + entity.toString());
			orderItemRepository.save(OrderUtil.toOrderItemEntity(oi, entity.get(), false));
		} else {
			throw new ResourceNotFoundException("Customer address not exist");
		}
	}

	@Override
	@Transactional
	public Order updateOrder(Order order) throws ResourceNotFoundException {
		if (orderItemRepository.findByIdOrderId(order.getOrderId()).isEmpty()
				&& orderPaymentRepository.findByIdOrderId(order.getOrderId()).isEmpty()) {
			throw new ResourceNotFoundException("Order not exist");
		}

		for (OrderItem oi : order.getOrderItemList()) {
			log.debug("get order updated:" + oi.toString());
			saveOrUpdateOrderItem(order.getOrderId(), oi);
		}

		for (OrderPayment op : order.getOrderPaymentList()) {
			log.debug("get payment updated:" + op.toString());
			saveOrUpdateOrderPayment(order.getOrderId(), op);
		}
		return order;
	}

	@Override
	@Transactional
	public boolean deleteOrder(String orderId) {
		boolean isDeleted = false;
		for (OrderItemEntity oiEnt : orderItemRepository.findByIdOrderId(orderId)) {
			log.debug("get item deleted:" + oiEnt.toString());
			oiEnt.setStatus("Cancelled");
			oiEnt.setModifiedAt(new Timestamp(System.currentTimeMillis()));
			orderItemRepository.save(oiEnt);
			isDeleted = true;
		}
		for (OrderPaymentEntity opEnt : orderPaymentRepository.findByIdOrderId(orderId)) {
			log.debug("get payment deleted:" + opEnt.toString());
			opEnt.setConfirmationNo("Cancelled");
			opEnt.setModifiedAt(new Timestamp(System.currentTimeMillis()));
			orderPaymentRepository.save(opEnt);
			isDeleted = true;
		}
		
		return isDeleted;
	}
}
