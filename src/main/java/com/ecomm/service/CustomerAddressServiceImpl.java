package com.ecomm.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.bo.CustomerAddress;
import com.ecomm.jpa.entity.CustomerAddressEntity;
import com.ecomm.jpa.entity.CustomerAddressEntityPK;
import com.ecomm.jpa.repository.CustomerAddressRepository;
import com.ecomm.util.CustomerUtil;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {

	private static final Logger log = LoggerFactory.getLogger(CustomerAddressServiceImpl.class);

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	@Override
	public List<CustomerAddress> getAllAddresses() {
		List<CustomerAddress> listofAddr = new ArrayList<>();
		for (CustomerAddressEntity custAddr : customerAddressRepository.findAll()) {
			listofAddr.add(CustomerUtil.toCustAddressBo(custAddr));
		}
		log.debug("get all returned size {}", listofAddr.size());
		return listofAddr;
	}

	@Override
	public CustomerAddress getAddress(String addressId, String custId) {
		CustomerAddress customerAddress = null;
		CustomerAddressEntityPK caEntPK = new CustomerAddressEntityPK();
		caEntPK.setAddressId(addressId);
		caEntPK.setCustId(custId);
		Optional<CustomerAddressEntity> entity = customerAddressRepository.findById(caEntPK);
		if (entity.isPresent()) {
			log.debug("get entity returned:" + entity.toString());
			customerAddress = CustomerUtil.toCustAddressBo(entity.get());
		}
		return customerAddress;
	}

	@Override
	public CustomerAddress saveAddress(CustomerAddress customerAddress) {
		CustomerAddressEntityPK caEntPK = new CustomerAddressEntityPK();
		caEntPK.setAddressId(customerAddress.getAddressId());
		caEntPK.setCustId(customerAddress.getCustId());
		if (customerAddress.getAddressId() == null || !customerAddressRepository.existsById(caEntPK)) {
			log.debug("get customerAddress saved:" + customerAddress.toString());
			return CustomerUtil.toCustAddressBo(
					customerAddressRepository.save(CustomerUtil.toCustAddressEntity(customerAddress, false)));
		} else {
			log.warn("save customerAddress failed with conflict {}" + customerAddress.toString());
			return null;
		}
	}

	@Override
	public CustomerAddress updateAddress(CustomerAddress customerAddress) {
		CustomerAddressEntityPK caEntPK = new CustomerAddressEntityPK();
		caEntPK.setAddressId(customerAddress.getAddressId());
		caEntPK.setCustId(customerAddress.getCustId());

		if (customerAddressRepository.existsById(caEntPK)) {
			log.debug("get customerAddress updated:" + customerAddress.toString());
			return CustomerUtil.toCustAddressBo(
					customerAddressRepository.save(CustomerUtil.toCustAddressEntity(customerAddress, true)));
		} else {
			log.warn("update customerAddress failed with no customer found {}" + customerAddress.toString());
			return null;
		}
	}

	@Override
	public boolean deleteAddress(String addressId, String custId) {
		CustomerAddressEntityPK caEntPK = new CustomerAddressEntityPK();
		caEntPK.setAddressId(addressId);
		caEntPK.setCustId(custId);

		if (customerAddressRepository.existsById(caEntPK)) {
			CustomerAddressEntity entity = customerAddressRepository.findById(caEntPK).get();
			log.debug("get customerAddress deleted:" + entity.toString());
			entity.setIsActive((byte) 0);
			entity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
			customerAddressRepository.save(entity);
			return true;
		} else {
			log.warn("delete customerAddress failed with no customer found {}");
			return false;
		}
	}

}
