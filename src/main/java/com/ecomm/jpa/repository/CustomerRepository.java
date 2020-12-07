package com.ecomm.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.jpa.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

}