package com.ecomm.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomm.bo.Item;
import com.ecomm.jpa.entity.ItemEntity;
import com.ecomm.jpa.repository.ItemRepository;
import com.ecomm.util.OrderUtil;

@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger log = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemRepository customerRepository;

	@Override
	public List<Item> getAllItems() {
		List<Item> listofItem = new ArrayList<>();
		for (ItemEntity cust : customerRepository.findAll()) {
			listofItem.add(OrderUtil.toItemBo(cust));
		}
		log.debug("get all returned size {}", listofItem.size());
		return listofItem;
	}

	@Override
	public Item getItem(String customerId) {
		Item customer = null;
		Optional<ItemEntity> entity = customerRepository.findById(customerId);
		if (entity.isPresent()) {
			log.debug("get itemEntity returned:" + entity.toString());
			customer = OrderUtil.toItemBo(entity.get());
		}
		return customer;
	}

	@Override
	public Item saveItem(Item customer) {
		if (customer.getItemId() == null || !customerRepository.existsById(customer.getItemId())) {
			log.debug("get item saved:" + customer.toString());
			return OrderUtil.toItemBo(customerRepository.save(OrderUtil.toItemEntity(customer, false)));
		} else {
			log.warn("save item failed with conflict {}" + customer.toString());
			return null;
		}
	}

	@Override
	public Item updateItem(Item customer) {
		if (customerRepository.existsById(customer.getItemId())) {
			log.debug("get item updated:" + customer.toString());
			return OrderUtil.toItemBo(customerRepository.save(OrderUtil.toItemEntity(customer, true)));
		} else {
			log.warn("update item failed with no customer found {}" + customer.toString());
			return null;
		}
	}

	@Override
	public boolean deleteItem(String customerId) {
		if (customerRepository.existsById(customerId)) {
			// customerRepository.deleteById(customerId);
			ItemEntity entity = customerRepository.findById(customerId).get();
			log.debug("get item deleted:" + entity.toString());
			entity.setIsAvailable((byte) 0);
			entity.setModifiedAt(new Timestamp(System.currentTimeMillis()));
			customerRepository.save(entity);
			return true;
		} else {
			log.warn("delete item failed with no customer found {}");
			return false;
		}
	}
}
