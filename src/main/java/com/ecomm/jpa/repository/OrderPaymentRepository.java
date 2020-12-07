package com.ecomm.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.jpa.entity.OrderPaymentEntity;
import com.ecomm.jpa.entity.OrderPaymentEntityPK;

public interface OrderPaymentRepository extends JpaRepository<OrderPaymentEntity, OrderPaymentEntityPK> {
	List<OrderPaymentEntity> findByIdOrderId(String orderId);
}