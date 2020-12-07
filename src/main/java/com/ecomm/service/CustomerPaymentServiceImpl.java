package com.ecomm.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.bo.CustomerPayment;
import com.ecomm.jpa.entity.CustomerPaymentEntity;
import com.ecomm.jpa.entity.CustomerPaymentEntityPK;
import com.ecomm.jpa.repository.CustomerPaymentRepository;
import com.ecomm.util.CustomerUtil;

@Service
public class CustomerPaymentServiceImpl implements CustomerPaymentService {

	private static final Logger log = LoggerFactory.getLogger(CustomerPaymentServiceImpl.class);

	@Autowired
	private CustomerPaymentRepository customerPaymentRepository;

	@Override
	public List<CustomerPayment> getAllPayments() {
		List<CustomerPayment> listofPayments = new ArrayList<>();
		for (CustomerPaymentEntity custPayment : customerPaymentRepository.findAll()) {
			listofPayments.add(CustomerUtil.toCustPaymentBo(custPayment));
		}
		log.debug("get all returned size {}", listofPayments.size());
		return listofPayments;
	}

	@Override
	public CustomerPayment getPayment(String paymentId, String custId) {
		CustomerPayment customerPayment = null;
		CustomerPaymentEntityPK cpEntPK = new CustomerPaymentEntityPK();
		cpEntPK.setPaymentId(paymentId);
		cpEntPK.setCustId(custId);
		Optional<CustomerPaymentEntity> entity = customerPaymentRepository.findById(cpEntPK);
		if (entity.isPresent()) {
			log.debug("get custEntity returned:" + entity.toString());
			customerPayment = CustomerUtil.toCustPaymentBo(entity.get());
		}
		return customerPayment;
	}

	@Override
	public CustomerPayment savePayment(CustomerPayment customerPayment) {
		CustomerPaymentEntityPK cpEntPK = new CustomerPaymentEntityPK();
		cpEntPK.setPaymentId(customerPayment.getPaymentId());
		cpEntPK.setCustId(customerPayment.getCustId());
		if (customerPayment.getPaymentId() == null || !customerPaymentRepository.existsById(cpEntPK)) {
			log.debug("get customerPayment saved:" + customerPayment.toString());
			return CustomerUtil.toCustPaymentBo(
					customerPaymentRepository.save(CustomerUtil.toCustPaymentEntity(customerPayment, false)));
		} else {
			log.warn("save customerPayment failed with conflict {}" + customerPayment.toString());
			return null;
		}
	}

	@Override
	public CustomerPayment updatePayment(CustomerPayment customerPayment) {
		CustomerPaymentEntityPK cpEntPK = new CustomerPaymentEntityPK();
		cpEntPK.setPaymentId(customerPayment.getPaymentId());
		cpEntPK.setCustId(customerPayment.getCustId());

		if (customerPaymentRepository.existsById(cpEntPK)) {
			log.debug("get customerPayment updated:" + customerPayment.toString());
			return CustomerUtil.toCustPaymentBo(
					customerPaymentRepository.save(CustomerUtil.toCustPaymentEntity(customerPayment, true)));
		} else {
			log.warn("update customerPayment failed with no customer found {}" + customerPayment.toString());
			return null;
		}
	}

	@Override
	public boolean deletePayment(String paymentId, String custId) {
		CustomerPaymentEntityPK cpEntPK = new CustomerPaymentEntityPK();
		cpEntPK.setPaymentId(paymentId);
		cpEntPK.setCustId(custId);

		if (customerPaymentRepository.existsById(cpEntPK)) {
			CustomerPaymentEntity entity = customerPaymentRepository.findById(cpEntPK).get();
			log.debug("get entity deleted:" + entity.toString());
			entity.setIsActive((byte) 0);
			entity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
			customerPaymentRepository.save(entity);
			return true;
		} else {
			log.warn("delete customerPayment failed with no customer found {}");
			return false;
		}
	}
}
