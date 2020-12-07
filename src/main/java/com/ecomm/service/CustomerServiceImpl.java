package com.ecomm.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.bo.Customer;
import com.ecomm.jpa.entity.CustomerEntity;
import com.ecomm.jpa.repository.CustomerRepository;
import com.ecomm.util.CustomerUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> listofCust = new ArrayList<>();
		for (CustomerEntity cust : customerRepository.findAll()) {
			listofCust.add(CustomerUtil.toCustBo(cust));
		}
		log.debug("get all returned size {}", listofCust.size());
		return listofCust;
	}

	@Override
	public Customer getCustomer(String customerId) {
		Customer customer = null;
		Optional<CustomerEntity> entity = customerRepository.findById(customerId);
		if (entity.isPresent()) {
			log.debug("get custEntity returned:" + entity.toString());
			customer = CustomerUtil.toCustBo(entity.get());
		}
		return customer;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		if (customer.getCustId() == null || !customerRepository.existsById(customer.getCustId())) {
			log.debug("get customer saved {}" + customer.toString());
			return CustomerUtil.toCustBo(customerRepository.save(CustomerUtil.toCustEntity(customer, false)));
		} else {
			log.warn("save customer failed with conflict {}" + customer.toString());
			return null;
		}
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		if (customerRepository.existsById(customer.getCustId())) {
			log.debug("get customer updated:" + customer.toString());
			return CustomerUtil.toCustBo(customerRepository.save(CustomerUtil.toCustEntity(customer, true)));
		} else {
			log.warn("update customer failed with no customer found {}" + customer.toString());
			return null;
		}
	}

	@Override
	public boolean deleteCustomer(String customerId) {
		if (customerRepository.existsById(customerId)) {
			CustomerEntity entity = customerRepository.findById(customerId).get();
			log.debug("get entity deleted:" + entity.toString());
			entity.setIsActive((byte) 0);
			entity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
			customerRepository.save(entity);
			return true;
		} else {
			log.warn("delete customer failed with no customer found {}");
			return false;
		}
	}
}
