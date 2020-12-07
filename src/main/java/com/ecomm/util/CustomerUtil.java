package com.ecomm.util;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.ecomm.bo.Customer;
import com.ecomm.bo.CustomerAddress;
import com.ecomm.bo.CustomerPayment;
import com.ecomm.jpa.entity.CustomerAddressEntity;
import com.ecomm.jpa.entity.CustomerAddressEntityPK;
import com.ecomm.jpa.entity.CustomerEntity;
import com.ecomm.jpa.entity.CustomerPaymentEntity;
import com.ecomm.jpa.entity.CustomerPaymentEntityPK;

public class CustomerUtil {

	private static final Logger log = LoggerFactory.getLogger(CustomerUtil.class);

	public static Customer toCustBo(CustomerEntity customerEn) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerEn, customer);
		log.debug("get custEntity converted to custBo:" + customer.toString());
		return customer;
	}

	public static CustomerEntity toCustEntity(Customer customer, boolean isUpdate) {
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customer, customerEntity);
		log.debug("get custBo converted to custEntity:" + customer.toString());
		if (customerEntity.getCustId() == null) {
			customerEntity.setCustId(UUIDUtil.getUUID());
		}
		customerEntity.setIsActive((byte) 1);
		if (isUpdate)
			customerEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		else
			customerEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return customerEntity;
	}

	public static CustomerAddress toCustAddressBo(CustomerAddressEntity customerAddressEn) {
		CustomerAddress customerAddress = new CustomerAddress();
		BeanUtils.copyProperties(customerAddressEn, customerAddress);
		log.debug("get custAddressBo converted to custAddressEntity:" + customerAddress.toString());
		customerAddress.setCustId(customerAddressEn.getId().getCustId());
		customerAddress.setAddressId(customerAddressEn.getId().getAddressId());
		return customerAddress;
	}

	public static CustomerAddressEntity toCustAddressEntity(CustomerAddress customerAddress, boolean isUpdate) {
		CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
		BeanUtils.copyProperties(customerAddress, customerAddressEntity);
		log.debug("get custAddressBo converted to custAddressEntity:" + customerAddressEntity.toString());
		CustomerAddressEntityPK caEntPK = new CustomerAddressEntityPK();
		caEntPK.setCustId(customerAddress.getCustId());
		if (customerAddress.getAddressId() == null) {
			caEntPK.setAddressId(UUIDUtil.getUUID());
		} else {
			caEntPK.setAddressId(customerAddress.getAddressId());
		}
		customerAddressEntity.setId(caEntPK);
		customerAddressEntity.setIsActive((byte) 1);
		if (isUpdate)
			customerAddressEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		else
			customerAddressEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));

		return customerAddressEntity;
	}

	public static CustomerPayment toCustPaymentBo(CustomerPaymentEntity customerPaymentEn) {
		CustomerPayment customerPayment = new CustomerPayment();
		BeanUtils.copyProperties(customerPaymentEn, customerPayment);
		log.debug("get custPaymentBo converted to custPaymentEntity:" + customerPayment.toString());
		customerPayment.setCustId(customerPaymentEn.getId().getCustId());
		customerPayment.setPaymentId(customerPaymentEn.getId().getPaymentId());
		return customerPayment;
	}

	public static CustomerPaymentEntity toCustPaymentEntity(CustomerPayment customerPayment, boolean isUpdate) {
		CustomerPaymentEntity customerPaymentEntity = new CustomerPaymentEntity();
		BeanUtils.copyProperties(customerPayment, customerPaymentEntity);
		log.debug("get custPaymentBo converted to custAddressEntity:" + customerPaymentEntity.toString());
		CustomerPaymentEntityPK cpEntPK = new CustomerPaymentEntityPK();
		cpEntPK.setCustId(customerPayment.getCustId());
		if (customerPayment.getPaymentId() == null) {
			cpEntPK.setPaymentId(UUIDUtil.getUUID());
		} else {
			cpEntPK.setPaymentId(customerPayment.getPaymentId());
		}
		customerPaymentEntity.setId(cpEntPK);
		customerPaymentEntity.setIsActive((byte) 1);

		if (isUpdate)
			customerPaymentEntity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
		else
			customerPaymentEntity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		return customerPaymentEntity;
	}
}
