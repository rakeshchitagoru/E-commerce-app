package com.ecomm.rest.controller;

import com.ecomm.util.UUIDUtil;

public class BaseController {

	private String requestId;

	public String getRequestId() {
		return requestId;
	}
	
	public BaseController() {
		requestId = UUIDUtil.getUUID();
	}
	
}
