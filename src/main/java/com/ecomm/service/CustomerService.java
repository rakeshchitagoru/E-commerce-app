package com.ecomm.service;

import java.util.List;

import com.ecomm.bo.Customer;

public interface CustomerService {
	public List<Customer> getAllCustomers();

	public Customer getCustomer(String customerId);

	public Customer saveCustomer(Customer customer);

	public Customer updateCustomer(Customer customer);

	public boolean deleteCustomer(String customerId);
}
