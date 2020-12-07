package com.ecomm.util;

import com.fasterxml.uuid.Generators;

public class UUIDUtil {

	public static String getUUID() {
		return Generators.timeBasedGenerator().generate().toString();
	}
}
