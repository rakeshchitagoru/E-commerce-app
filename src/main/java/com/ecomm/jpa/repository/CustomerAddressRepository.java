package com.ecomm.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.jpa.entity.CustomerAddressEntity;
import com.ecomm.jpa.entity.CustomerAddressEntityPK;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, CustomerAddressEntityPK> {

}