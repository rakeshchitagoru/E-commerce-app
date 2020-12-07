package com.ecomm.util;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.ecomm.bo.Item;
import com.ecomm.bo.OrderDetails;
import com.ecomm.bo.OrderItem;
import com.ecomm.bo.OrderItemDetail;
import com.ecomm.bo.OrderPayment;
import com.ecomm.bo.OrderPaymentDetail;
import com.ecomm.jpa.entity.CustomerAddressEntity;
import com.ecomm.jpa.entity.CustomerPaymentEntity;
import com.ecomm.jpa.entity.ItemEntity;
import com.ecomm.jpa.entity.OrderItemEntity;
import com.ecomm.jpa.entity.OrderItemEntityPK;
import com.ecomm.jpa.entity.OrderPaymentEntity;
import com.ecomm.jpa.entity.OrderPaymentEntityPK;

public class OrderUtil {

	public static Item toItemBo(ItemEntity itemEn) {
		Item item = new Item();
		BeanUtils.copyProperties(itemEn, item);
		return item;
	}

	public static ItemEntity toItemEntity(Item item, boolean isUpdate) {
		ItemEntity itemEntity = new ItemEntity();
		BeanUtils.copyProperties(item, itemEntity);
		if (itemEntity.getItemId() == null) {
			itemEntity.setItemId(UUIDUtil.getUUID());
		}
		itemEntity.setIsAvailable((byte) 1);
		if (isUpdate)
			itemEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		else
			itemEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return itemEntity;
	}

	public static OrderItemDetail toOrderItemBo(OrderItemEntity orderItemEn) {
		OrderItemDetail orderItemDetail = new OrderItemDetail();
		BeanUtils.copyProperties(orderItemEn, orderItemDetail);
		orderItemDetail.setOrderId(orderItemEn.getId().getOrderId());
		orderItemDetail.setItem(toItemBo(orderItemEn.getItem()));
		orderItemDetail.setCustomerAddress(CustomerUtil.toCustAddressBo(orderItemEn.getCustomerAddress()));
		orderItemDetail.setCustomer(CustomerUtil.toCustBo(orderItemEn.getCustomerAddress().getCustomer()));
		return orderItemDetail;
	}

	public static OrderItemEntity toOrderItemEntity(OrderItem orderItem, CustomerAddressEntity ca, boolean isUpdate) {
		OrderItemEntity orderItemEntity = new OrderItemEntity();
		BeanUtils.copyProperties(orderItem, orderItemEntity);

		OrderItemEntityPK oiEntPK = new OrderItemEntityPK();
		oiEntPK.setItemId(orderItem.getItemId());

		if (orderItem.getOrderId() == null) {
			oiEntPK.setOrderId(UUIDUtil.getUUID());
		} else {
			oiEntPK.setOrderId(orderItem.getOrderId());
		}
		orderItemEntity.setId(oiEntPK);

		orderItemEntity.setCustomerAddress(ca);

		if (isUpdate)
			orderItemEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		else
			orderItemEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return orderItemEntity;
	}

	public static OrderPaymentDetail toOrderPaymentBo(OrderPaymentEntity orderPaymentEn) {
		OrderPaymentDetail orderPaymentDetail = new OrderPaymentDetail();
		BeanUtils.copyProperties(orderPaymentEn, orderPaymentDetail);
		orderPaymentDetail.setOrderId(orderPaymentEn.getId().getOrderId());
		orderPaymentDetail.setCustomer(CustomerUtil.toCustBo(orderPaymentEn.getCustomerPayment().getCustomer()));
		orderPaymentDetail.setCustomerPayment(CustomerUtil.toCustPaymentBo(orderPaymentEn.getCustomerPayment()));
		return orderPaymentDetail;
	}

	public static OrderPaymentEntity toOrderPaymentEntity(OrderPayment orderPayment, CustomerPaymentEntity cpEnt,
			boolean isUpdate) {
		OrderPaymentEntity orderPaymentEntity = new OrderPaymentEntity();
		BeanUtils.copyProperties(orderPayment, orderPaymentEntity);

		OrderPaymentEntityPK opEntPK = new OrderPaymentEntityPK();
		opEntPK.setOrderPaymentId(orderPayment.getOrderPaymentId());
		orderPaymentEntity.setId(opEntPK);

		if (orderPayment.getOrderId() == null) {
			opEntPK.setOrderId(UUIDUtil.getUUID());
		} else {
			opEntPK.setOrderId(orderPayment.getOrderId());
		}
		orderPaymentEntity.setId(opEntPK);

		orderPaymentEntity.setCustomerPayment(cpEnt);

		if (isUpdate)
			orderPaymentEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		else
			orderPaymentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return orderPaymentEntity;
	}

	public static List<OrderDetails> prepareOrders(List<OrderItemDetail> listofOrderItems,
			List<OrderPaymentDetail> listofOrderPayments) {
		Map<String, OrderDetails> orderMap = new HashMap<>();

		for (OrderPaymentDetail op : listofOrderPayments) {
			orderMap.computeIfAbsent(op.getOrderId(), k -> new OrderDetails(op.getOrderId())).addOrderPayment(op);
		}

		for (OrderItemDetail oi : listofOrderItems) {
			orderMap.computeIfAbsent(oi.getOrderId(), k -> new OrderDetails(oi.getOrderId())).addOrderItem(oi);
		}

		return new ArrayList<OrderDetails>(orderMap.values());
	}

}
