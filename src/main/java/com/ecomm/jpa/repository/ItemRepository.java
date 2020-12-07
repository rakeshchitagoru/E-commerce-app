package com.ecomm.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecomm.jpa.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {

}