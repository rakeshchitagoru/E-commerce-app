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

import com.ecomm.bo.CustomerAddress;
import com.ecomm.service.CustomerAddressService;

@RestController
@RequestMapping("/api")

public class CustomerAddressController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(CustomerAddressController.class);

	@Autowired
	private CustomerAddressService customerAddressService;

	@GetMapping("/customeraddress")
	public ResponseEntity<?> getCustomerAddresses() {
		log.error(getRequestId());
		List<CustomerAddress> custAddList = customerAddressService.getAllAddresses();
		if (!custAddList.isEmpty()) {
			log.debug("get all returned for requestId {} count {}", getRequestId(), custAddList.size());
			return ResponseEntity.status(HttpStatus.OK).body(custAddList);
		} else {
			log.warn("Request failed");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/customeraddress/{id}/{custid}")
	public ResponseEntity<?> getCustomerAddressById(@PathVariable(value = "id") String addressId,
			@PathVariable(value = "custid") String custId) {
		log.error(getRequestId());
		CustomerAddress customerAddress = customerAddressService.getAddress(addressId, custId);
		if (customerAddress != null) {
			log.debug("get id returned:" + customerAddress.toString());
			return ResponseEntity.status(HttpStatus.OK).body(customerAddress);
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/customeraddress")
	public ResponseEntity<?> createCustomerAddress(@Valid @RequestBody CustomerAddress customerAddress) {
		log.error(getRequestId());
		CustomerAddress customerAddressRes = customerAddressService.saveAddress(customerAddress);
		if (customerAddressRes != null) {
			log.debug("get address saved:" + customerAddressRes.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(customerAddressRes);
		} else {
			log.warn("Customer already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(customerAddress);
		}
	}

	@PutMapping("/customeraddress")
	public ResponseEntity<?> updateCustomerAddress(@Valid @RequestBody CustomerAddress customerAddress) {
		log.error(getRequestId());
		CustomerAddress customerAddressRes = customerAddressService.updateAddress(customerAddress);
		if (customerAddressRes != null) {
			log.debug("get address updated:" + customerAddressRes.toString());
			return ResponseEntity.status(HttpStatus.OK).body(customerAddressRes);
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customerAddress);
		}
	}

	@DeleteMapping("/customeraddress/{id}/{custid}")
	public ResponseEntity<?> deleteCustomerAddress(@PathVariable(value = "id") String addressId,
			@PathVariable(value = "custid") String custId) {
		log.error(getRequestId());
		boolean isDeleted = customerAddressService.deleteAddress(addressId, custId);
		if (isDeleted) {
			log.debug("get address deleted:" + isDeleted);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.warn("Customer doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
