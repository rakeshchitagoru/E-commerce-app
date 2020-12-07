package com.ecomm.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.jpa.entity.CustomerPaymentEntity;
import com.ecomm.jpa.entity.CustomerPaymentEntityPK;

public interface CustomerPaymentRepository extends JpaRepository<CustomerPaymentEntity, CustomerPaymentEntityPK> {

}