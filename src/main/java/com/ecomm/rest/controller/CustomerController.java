package com.ecomm.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.bo.Customer;
import com.ecomm.service.CustomerService;

@RestController
@RequestMapping("/api")
@Validated
public class CustomerController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	public ResponseEntity<?> getCustomers() {
		List<Customer> custList = customerService.getAllCustomers();
		if (!custList.isEmpty()) {
			log.debug("get all returned for requestId {} count {}", getRequestId(), custList.size());
			return ResponseEntity.status(HttpStatus.OK).body(custList);
		} else {
			log.warn("Request failed");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable(value = "id") String customerId) {
		Customer customer = customerService.getCustomer(customerId);
		if (customer != null) {
			log.debug("get id returned {}" + customer.toString());
			return ResponseEntity.status(HttpStatus.OK).body(customer);
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody @Valid Customer customer) {
		Customer customerRes = customerService.saveCustomer(customer);
		if (customerRes != null) {
			log.debug("get customer saved:" + customerRes.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(customerRes);
		} else {
			log.warn("Customer already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(customer);
		}
	}

	@PutMapping("/customer")
	public ResponseEntity<?> updateCustomer(@RequestBody @Valid Customer customer) {
		Customer customerRes = customerService.updateCustomer(customer);
		if (customerRes != null) {
			log.debug("get customer updated:" + customerRes);
			return ResponseEntity.status(HttpStatus.OK).body(customerRes);
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customer);
		}
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") String customerId) {
		boolean isDeleted = customerService.deleteCustomer(customerId);
		if (isDeleted) {
			log.debug("get customer deleted:" + isDeleted);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
