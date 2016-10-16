
package org.cd.sport.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.vo.KV;

public class Constants {

	public static final class Common {

		public static final int PAGE_SIZE = 15;

		public static final int ACTIVE = 1;

		public static final int UN_ACTIVE = -1;

		public static final String VERIF_CODE = "verif_code";

	}

	public static final class Dic {

		public static final String DEFAULT_PID = "-1";

	}

	public static final class User {

		// 性别男
		public static final int MAN = 0;
		// 性别女
		public static final int WOMAN = 1;

		public static final String DEFAULT_PASSWORD = "111111";

		public static final String RSA_KEY = "RSA_KEY";

		public static final String UUID_KEY = "UUID_KEY";

		public static Map<String, String> urlMapping = new HashMap<String, String>();

		static {
			urlMapping.put("ROLE_KJS_ADMIN", "portal/kjsadmin/index.htm");
			urlMapping.put("ROLE_KJS_LEADER", "portal/kjsleader/index.htm");
			urlMapping.put("ROLE_KJS_EXPERT", "portal/kjsexpert/index.htm");
			urlMapping.put("ROLE_SB_ADMIN", "portal/sbadmin/index.htm");
			urlMapping.put("ROLE_SB_OPER", "portal/sboper/index.htm");
			urlMapping.put("ROLE_ORG_ADMIN", "portal/orgadmin/index.htm");
			urlMapping.put("ROLE_ORG_OPER", "portal/orgoper/index.htm");
		}

		public static boolean isActive(int status) {
			return Constants.Common.ACTIVE == status;
		}

		public static int parseGender(String gender) {
			if (StringUtils.isBlank(gender)) {
				return MAN;
			}
			try {
				int parseInt = Integer.parseInt(gender);
				return parseInt == WOMAN ? WOMAN : MAN;
			} catch (Exception e) {
				return MAN;
			}

		}
	}

	public static final class Role {
		// 科教司管理员角色
		public static String ROLE_KJS_ADMIN = "ROLE_KJS_ADMIN";
		// 科教司领导角色
		public static String ROLE_KJS_LEADER = "ROLE_KJS_LEADER";
		// 科教司专家角色
		public static String ROLE_KJS_EXPERT = "ROLE_KJS_EXPERT";
		// 申报单位管理员角色
		public static String ROLE_SB_ADMIN = "ROLE_SB_ADMIN";
		// 申报单位操作员角色
		public static String ROLE_SB_OPER = "ROLE_SB_OPER";
		// 组织单位管理员角色
		public static String ROLE_ORG_ADMIN = "ROLE_ORG_ADMIN";
		// 组织单位操作角色
		public static String ROLE_ORG_OPER = "ROLE_ORG_OPER";

		/**
		 * 判断用户是否有创建用户权限
		 */
		public static boolean hasOper(String role) {
			return ROLE_KJS_ADMIN.equals(role) || ROLE_SB_ADMIN.equals(role) || ROLE_ORG_ADMIN.equals(role);
		}

		/**
		 * 判断当前登录的角色是否对其他角色有操作权限 role1是否对role2有操作权限
		 */
		public static boolean hasOper(String role1, String role2) {
			if (hasOper(role1)) {
				// 如果被操作的为科教司管理员
				if (isAdmin(role2)) {
					return false;
				}
				// 如果操作的为科教司管理员
				if (isAdmin(role1)) {
					return true;
				}
				// 如果单位管理员角色
				if (ROLE_SB_ADMIN.equals(role1)) {
					if (ROLE_SB_OPER.equals(role2)) {
						return true;
					}
				}
				// 组织单位管理员角色
				if (ROLE_ORG_ADMIN.equals(role1)) {
					if (ROLE_ORG_OPER.equals(role2)) {
						return true;
					}
				}

			}
			return false;
		}

		/**
		 * 判断用户是否科教司管理员
		 */
		public static boolean isAdmin(String role) {
			return ROLE_KJS_ADMIN.equals(role);
		}

		/**
		 * 查询用户能创建的角色
		 */
		public static List<KV> getRoles(String role) {
			List<KV> kvs = new ArrayList<KV>();
			// 科教司管理员角色
			if (ROLE_KJS_ADMIN.equals(role)) {
				kvs.add(new KV("领导", ROLE_KJS_LEADER));
				kvs.add(new KV("专家", ROLE_KJS_EXPERT));
			} else if (ROLE_SB_ADMIN.equals(role)) {
				kvs.add(new KV("申报人员", ROLE_SB_OPER));
			} else if (ROLE_ORG_ADMIN.equals(role)) {
				kvs.add(new KV("管理人员", ROLE_ORG_ADMIN));
				kvs.add(new KV("操作人员", ROLE_ORG_OPER));
			}
			return kvs;
		}

		public static String[] getQueryRoles(String role) {
			// 科教司管理员角色
			if (ROLE_KJS_ADMIN.equals(role)) {
				return new String[] { ROLE_KJS_ADMIN, ROLE_KJS_LEADER, ROLE_KJS_EXPERT };
			} else if (ROLE_SB_ADMIN.equals(role)) {
				return new String[] { ROLE_SB_ADMIN, ROLE_SB_OPER };
			} else if (ROLE_ORG_ADMIN.equals(role)) {
				return new String[] { ROLE_ORG_ADMIN, ROLE_ORG_OPER };
			}
			return null;
		}

	}

}