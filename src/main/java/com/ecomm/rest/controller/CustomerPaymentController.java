package com.ecomm.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.bo.CustomerPayment;
import com.ecomm.service.CustomerPaymentService;

@RestController
@RequestMapping("/api")

public class CustomerPaymentController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(CustomerPaymentController.class);

	@Autowired
	private CustomerPaymentService customerPaymentService;

	@GetMapping("/customerpayment")
	public ResponseEntity<?> getCustomerPaymentes() {
		log.error(getRequestId());
		List<CustomerPayment> custPayList = customerPaymentService.getAllPayments();
		if (!custPayList.isEmpty()) {
			log.debug("get all returned for requestId {} count {}", getRequestId(), custPayList.size());
			return ResponseEntity.status(HttpStatus.OK).body(custPayList);
		} else {
			log.warn("Request failed");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/customerpayment/{id}/{custid}")
	public ResponseEntity<?> getCustomerPaymentById(@PathVariable(value = "id") String paymentId,
			@PathVariable(value = "custid") String custId) {
		log.error(getRequestId());
		CustomerPayment customerPayment = customerPaymentService.getPayment(paymentId, custId);
		if (customerPayment != null) {
			log.debug("get paymentId returned:" + customerPayment.toString());
			return ResponseEntity.status(HttpStatus.OK).body(customerPayment);
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/customerpayment")
	public ResponseEntity<?> createCustomerPayment(@Valid @RequestBody CustomerPayment customerPayment) {
		log.error(getRequestId());
		CustomerPayment customerPaymentRes = customerPaymentService.savePayment(customerPayment);
		if (customerPaymentRes != null) {
			log.debug("get customerPayment saved:" + customerPaymentRes.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(customerPaymentRes);
		} else {
			log.warn("Customer already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(customerPayment);
		}
	}

	@PutMapping("/customerpayment")
	public ResponseEntity<?> updateCustomerPayment(@Valid @RequestBody CustomerPayment customerPayment) {
		log.error(getRequestId());
		CustomerPayment customerPaymentRes = customerPaymentService.updatePayment(customerPayment);
		if (customerPaymentRes != null) {
			log.debug("get customerPayment updated:" + customerPaymentRes.toString());
			return ResponseEntity.status(HttpStatus.OK).body(customerPaymentRes);
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerPayment);
		}
	}

	@DeleteMapping("/customerpayment/{id}/{custid}")
	public ResponseEntity<?> deleteCustomerPayment(@PathVariable(value = "id") String addressId,
			@PathVariable(value = "custid") String custId) {
		log.error(getRequestId());
		boolean isDeleted = customerPaymentService.deletePayment(addressId, custId);
		if (isDeleted) {
			log.debug("get customerPayment deleted:" + isDeleted);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
