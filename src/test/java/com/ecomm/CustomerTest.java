package com.ecomm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.ecomm.bo.Customer;
import com.ecomm.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

	private static final Logger log = LoggerFactory.getLogger(CustomerTest.class);

	@Autowired
	CustomerService customerService;

	@Test
	@Transactional
	@Rollback(true)
	public void saveCustomerTest() {
		Customer c = createCustomer();
		Customer customer = customerService.saveCustomer(c);
		assertNotNull(customer);
		log.debug("get customer saved" + customer.toString());
	}

	private Customer createCustomer() {
		Customer c = new Customer();
		c.setEmail("abc@gmail.com");
		c.setFirstName("firstname");
		c.setLastName("lastname");
		c.setMiddleName("middlename");
		c.setPhoneNumber("phonenumber");
		c.setIsActive((byte) 1);
		log.debug("get customer created" + c.toString());
		return c;
	}

	@Test
	@Transactional
	@Rollback(true)
	public void getAllCustomersTest() {
		Customer c = createCustomer();
		customerService.saveCustomer(c);
		List<Customer> customerList = customerService.getAllCustomers();
		assertNotNull(customerList);
		System.out.println(customerList.size());
		assertThat(!customerList.isEmpty());
		log.debug("get customer returned" + c.toString());

	}
}
