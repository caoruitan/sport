package org.cd.sport.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDUtil {
	
	public static String getGuid() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSSSSS");
		return sf.format(new Date(System.currentTimeMillis()))
				+ UUID.randomUUID().toString().replace("-", "").substring(0, 20);
	}

	public static String getRandomUUID(int digit) {
		return UUID.randomUUID().toString().replace("-", "").substring(0, digit);
	}
}