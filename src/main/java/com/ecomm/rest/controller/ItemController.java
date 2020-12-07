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

import com.ecomm.bo.Item;
import com.ecomm.service.ItemService;

@RestController
@RequestMapping("/api")

public class ItemController extends BaseController {

	private static final Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService itemService;

	@GetMapping("/item")
	public ResponseEntity<?> getItems() {
		log.error(getRequestId());
		List<Item> itemList = itemService.getAllItems();
		if (!itemList.isEmpty()) {
			log.debug("get all returned for requestId {} count {}", getRequestId(), itemList.size());
			return ResponseEntity.status(HttpStatus.OK).body(itemList);
		} else {
			log.warn("Request failed");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<?> getItemById(@PathVariable(value = "id") String itemId) {
		log.error(getRequestId());
		Item item = itemService.getItem(itemId);
		if (item != null) {
			log.debug("get itemId returned:" + item.toString());
			return ResponseEntity.status(HttpStatus.OK).body(item);
		} else {
			log.warn("Item doesn't exist");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}

	@PostMapping("/item")
	public ResponseEntity<?> createItem(@Valid @RequestBody Item item) {
		log.error(getRequestId());
		Item itemRes = itemService.saveItem(item);
		if (itemRes != null) {
			log.debug("get item saved:" + itemRes.toString());
			return ResponseEntity.status(HttpStatus.CREATED).body(itemRes);
		} else {
			log.warn("Item already exists");
			return ResponseEntity.status(HttpStatus.CONFLICT).body(item);
		}
	}

	@PutMapping("/item")
	public ResponseEntity<?> updateItem(@Valid @RequestBody Item item) {
		log.error(getRequestId());
		Item itemRes = itemService.updateItem(item);
		if (itemRes != null) {
			log.debug("get item updated:" + itemRes.toString());
			return ResponseEntity.status(HttpStatus.OK).body(itemRes);
		} else {
			log.warn("Item doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(item);
		}
	}

	@DeleteMapping("/item/{id}")
	public ResponseEntity<?> deleteItem(@PathVariable(value = "id") String itemId) {
		log.error(getRequestId());
		boolean isDeleted = itemService.deleteItem(itemId);
		if (isDeleted) {
			log.debug("get item deleted:" + isDeleted);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			log.warn("Item doesn't exist");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
