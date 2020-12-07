package com.ecomm.util;

import java.math.BigDecimal;

import com.ecomm.bo.Customer;
import com.ecomm.bo.CustomerAddress;
import com.ecomm.bo.CustomerPayment;
import com.ecomm.bo.Item;
import com.ecomm.bo.Order;
import com.ecomm.bo.OrderItem;
import com.ecomm.bo.OrderPayment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonGen {
	public static void main(String[] args) throws JsonProcessingException {
		new JsonGen().genOrder();
	}

	void genCustomer() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Customer c = new Customer();
		c.setCustId("10");
		c.setEmail("abc@gmail.com");
		c.setFirstName("firstname");
		c.setLastName("lastname");
		c.setMiddleName("middlename");
		c.setPhoneNumber("phonenumber");
		c.setIsActive((byte) 1);
		System.out.println(objectMapper.writeValueAsString(c));
	}

	void genCustomerAddress() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		CustomerAddress c = new CustomerAddress();
		c.setCustId("10");
		c.setAddress1("address1");
		c.setAddressId("addid1");
		c.setCity("city");
		c.setCountry("USA");
		c.setDefault(true);
		c.setPhoneNumber("phonenumber");
		c.setState("TX");
		c.setZipcode("75063");

		System.out.println(objectMapper.writeValueAsString(c));
	}

	void genCustomerPayment() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		CustomerPayment c = new CustomerPayment();
		c.setPaymentId("");
		c.setCustId("15");
		c.setAddress("add");
		c.setCardNo("68945");
		c.setActive(true);
		c.setPaymentType("card");
		c.setDefault(true);
		c.setCity("Irv");
		c.setState("TX");
		c.setZipcode("75063");
		System.out.println(objectMapper.writeValueAsString(c));

	}

	void genItem() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Item item = new Item();
		item.setItemName("item1");
		item.setPrice(new BigDecimal(20.9));
		System.out.println(objectMapper.writeValueAsString(item));
	}

	void genOrder() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Order o = new Order();
		o.setOrderId("order1");

		for (int i = 1; i < 3; i++) {
			OrderItem oi = new OrderItem();
			oi.setAddressId("addid" + i);
			oi.setCustId("10");
			oi.setItemId("item" + i);
			oi.setPrice(new BigDecimal(5.6));
			oi.setQuantity(2);
			oi.setShippingType("HOME");
			oi.setStatus("R");
			o.addOrderItem(oi);
		}

		for (int i = 1; i < 3; i++) {
			OrderPayment op = new OrderPayment();
			op.setConfirmationNo("conf" + i);
			op.setCustId("10");
			op.setDiscountPrice(new BigDecimal(0));
			op.setOrderPaymentId("opid" + i);
			op.setShippingPrice(new BigDecimal(1));
			op.setTaxPrice(new BigDecimal(2));
			op.setTotalPrice(new BigDecimal(50.8));
			o.addOrderPayment(op);
		}

		System.out.println(objectMapper.writeValueAsString(o));
	}
}
