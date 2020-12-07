package com.ecomm.service;

import java.util.List;

import com.ecomm.bo.CustomerAddress;

public interface CustomerAddressService {
	public List<CustomerAddress> getAllAddresses();

	public CustomerAddress getAddress(String addressId, String custId);

	public CustomerAddress saveAddress(CustomerAddress customerAddress);

	public CustomerAddress updateAddress(CustomerAddress customerAddress);

	public boolean deleteAddress(String addressId, String custId);

}
