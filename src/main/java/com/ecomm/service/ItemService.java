package com.ecomm.service;

import java.util.List;

import com.ecomm.bo.Item;

public interface ItemService {
	public List<Item> getAllItems();

	public Item getItem(String itemId);

	public Item saveItem(Item item);

	public Item updateItem(Item item);

	public boolean deleteItem(String itemId);
}
