package org.cd.sport.constant;

import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static final class Common {

		public static final int ACTIVE = 1;

		public static final int UN_ACTIVE = -1;

	}

	public static final class User {

		public static final String RSA_KEY = "RSA_KEY";
		
		public static final String UUID_KEY = "UUID_KEY";

		public static Map<String, String> urlMapping = new HashMap<String, String>();

		static {
			urlMapping.put("ROLE_KJS", "kjs.htm");
			urlMapping.put("ROLE_ADMIN", "/admin");
			urlMapping.put("ROLE_ADMIN", "/admin");
			urlMapping.put("ROLE_ADMIN", "/admin");
			urlMapping.put("ROLE_ADMIN", "/admin");
		}

		public static boolean isActive(int status) {
			return Constants.Common.ACTIVE == status;
		}
	}

}
