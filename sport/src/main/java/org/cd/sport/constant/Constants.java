package org.cd.sport.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
			urlMapping.put("ROLE_ORG_OPER", "portal/orgoper/index.htm");
		}

		public static boolean isActive(int status) {
			return Constants.Common.ACTIVE == status;
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

		/**
		 * 判断用户是否有创建用户权限
		 */
		public static boolean hasOper(String role) {
			return ROLE_KJS_ADMIN.equals(role) || ROLE_SB_ADMIN.equals(role) || ROLE_ORG_ADMIN.equals(role);
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
				return new String[] { ROLE_ORG_ADMIN };
			}
			return null;
		}

	}
	
	public static final class Subject {
		
		public static final String SUBJECT_START_YEAR = "2015"; 
		
	}

}
