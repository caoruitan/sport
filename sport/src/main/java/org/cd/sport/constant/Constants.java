package org.cd.sport.constant;

public class Constants {

	public static final class Common {

		public static final int ACTIVE = 1;

		public static final int UN_ACTIVE = -1;

	}

	public static final class User {
		public static boolean isActive(int status) {
			return Constants.Common.ACTIVE == status;
		}
	}

}
