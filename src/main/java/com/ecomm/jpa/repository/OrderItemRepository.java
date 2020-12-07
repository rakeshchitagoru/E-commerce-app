package com.ecomm.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.jpa.entity.OrderItemEntity;
import com.ecomm.jpa.entity.OrderItemEntityPK;

public interface OrderItemRepository extends JpaRepository<OrderItemEntity, OrderItemEntityPK> {
	
	List<OrderItemEntity> findByIdOrderId(String orderId);

}