package com.ecomm.service;

import java.util.List;

import com.ecomm.bo.CustomerPayment;

public interface CustomerPaymentService {
	public List<CustomerPayment> getAllPayments();

	public CustomerPayment getPayment(String paymentId, String custId);

	public CustomerPayment savePayment(CustomerPayment customerPayment);

	public CustomerPayment updatePayment(CustomerPayment customerPayment);

	public boolean deletePayment(String paymentId, String custId);

}
