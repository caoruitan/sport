
package org.cd.sport.constant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.vo.KV;

public class Constants {

	public static final class Common {

		public static final int PAGE_SIZE = 10;

		public static final int ACTIVE = 1;

		public static final int UN_ACTIVE = -1;

		public static final String VERIF_CODE = "verif_code";

	}

	public static final class Dic {

		/**
		 * 数据字典顶级id
		 */
		public static final String DEFAULT_PID = "-1";
		/**
		 * 数据字典-证件类型code
		 */
		public static final String DIC_CRED_CODE = "001";

		/**
		 * 数据字典-职称
		 */
		public static final String DIC_ZC_CODE = "002";

		/**
		 * 数据字典-职务
		 */
		public static final String DIC_ZW_CODE = "003";

		/**
		 * 数据字典-学历
		 */
		public static final String DIC_DEGREES_CODE = "004";

		/**
		 * 数据字典-所在地区
		 */
		public static final String DIC_ADDRESS_CODE = "005";

		/**
		 * 数据字典-单位性质
		 */
		public static final String DIC_QUALITY_CODE = "006";

		/**
		 * 数据字典-课题活动
		 */
		public static final String DIC_KT_ACTVITY_CODE = "008";

		/**
		 * 数据字典-应用行业领域
		 */
		public static final String DIC_APP_CODE = "009";

		/**
		 * 数据字典-创新类型
		 */
		public static final String DIC_CREATE_CODE = "010";

		/**
		 * 数据字典-预期成果
		 */
		public static final String DIC_EXPECT_CODE = "011";

		/**
		 * 数据字典-密级
		 */
		public static final String DIC_SECRET_CODE = "014";

		/**
		 * 数据字典-申报书-经费来源
		 */
		public static final String DIC_SBS_INCOME_CODE = "015001";

		/**
		 * 数据字典-经费支出
		 */
		public static final String DIC_SBS_COST_CODE = "015002";

		/**
		 * 数据字典-科研总和
		 */
		public static final String DIC_SBS_KYCOST_TOTAL_CODE = "015002001";

		/**
		 * 数据字典-任务书-费用总额
		 */

		public static final String DIC_RWS_COST_CODE = "017";
		/**
		 * 数据字典-任务书-直接费用
		 */
		public static final String DIC_RWS_DIRECT_CODE = "017001001";

		/**
		 * 数据字典-任务书-间接费用
		 */
		public static final String DIC_RWS_INDIRECT_CODE = "017001002";

		/**
		 * 数据字典-科教司联系方式
		 */

		public static final String DIC_CONCAT_CODE = "020";

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

		public static String getGenderName(int gender) {
			if (WOMAN == gender) {
				return "女";
			}
			return "男";
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

	public static final class Subject {

		public static final String SUBJECT_START_YEAR = "2010";
		/**
		 * 申报书填写及评审
		 */
		public static final String SUBJECT_STAGE_SBSTB = "SBSTB";
		/**
		 * 任务书填报及审批
		 */
		public static final String SUBJECT_STAGE_RWSTB = "RWSTB";
		/**
		 * 阶段成果及审批
		 */
		public static final String SUBJECT_STAGE_JDBG = "JDBG";
		/**
		 * 结题报告
		 */
		public static final String SUBJECT_STAGE_JTBG = "JTBG";
		/**
		 * 结题结束
		 */
		public static final String SUBJECT_STAGE_JTEND = "JTEND";
		/**
		 * 回退状态
		 */
		public static final String SUBJECT_STAGE_JTBACK = "JTBACK";

		public static final String SUBJECT_TYPE_ZBKT = "ZBKT";

		public static final String SUBJECT_TYPE_KYGGKT = "KYGGKT";

		public static final String SUBJECT_ZBKT_SBS_END_DATE_CODE = "019002001";

		public static final String SUBJECT_ZBKT_RWS_END_DATE_CODE = "019002002";

		public static final String SUBJECT_ZBKT_END_DATE_CODE = "019002003";

		public static final String SUBJECT_KYGGKT_SBS_END_DATE_CODE = "019001001";

		public static final String SUBJECT_KYGGKT_RWS_END_DATE_CODE = "019001002";

		public static final String SUBJECT_KYGGKT_END_DATE_CODE = "019001003";

		public static Map<String, String> getSubjectTypes() {
			Map<String, String> types = new LinkedHashMap<String, String>();
			types.put(SUBJECT_TYPE_ZBKT, "招标课题");
			types.put(SUBJECT_TYPE_KYGGKT, "科技攻关课题");
			return types;
		}

		public static Map<String, String> getSubjectStages() {
			Map<String, String> types = new LinkedHashMap<String, String>();
			types.put(SUBJECT_STAGE_SBSTB, "申报书填报");
			types.put(SUBJECT_STAGE_RWSTB, "任务书填报");
			types.put(SUBJECT_STAGE_JDBG, "阶段报告");
			types.put(SUBJECT_STAGE_JTBG, "结题报告");
			return types;
		}
	}

	public static final class SubjectSbs {

		public static final String SUBJECT_SBS_STATUS_SBOPER_TB = "SBOPER_TB";

		public static final String SUBJECT_SBS_STATUS_SBADMIN_SP = "SBADMIN_SP";

		public static final String SUBJECT_SBS_STATUS_ORG_SP = "ORG_SP";

		public static final String SUBJECT_SBS_STATUS_KJS_SP = "KJS_SP";

		public static final String SUBJECT_SBS_STATUS_COMPLETE = "COMPLETE";

		public static final String SUBJECT_SBS_STATUS_BACK = "BACK";

		public static final String SUBJECT_SBS_DOC_TEMPLATE_PATH = "/doc/sbs_template.doc";

		public static final String SUBJECT_SBS_DESCRIPTION_NEWS_ID = "402881e42cc658b4012cc66387a70002";
		/**
		 * 主要申请人
		 */
		public static final String SUBJECT_SBS_PROPOSER_PRIMARY = "primary";
		/**
		 * 其他申请人
		 */
		public static final String SUBJECT_SBS_PROPOSER_OTHER = "other";

		public static Map<String, String> getSubjectSbsStatus() {
			Map<String, String> types = new LinkedHashMap<String, String>();
			types.put(SUBJECT_SBS_STATUS_SBOPER_TB, "申报人填报");
			types.put(SUBJECT_SBS_STATUS_SBADMIN_SP, "已提交至本单位管理员审批");
			types.put(SUBJECT_SBS_STATUS_ORG_SP, "已提交至组织单位审批");
			types.put(SUBJECT_SBS_STATUS_KJS_SP, "已提交至科教司审批");
			types.put(SUBJECT_SBS_STATUS_COMPLETE, "审批通过");
			types.put(SUBJECT_SBS_STATUS_BACK, "已回退");
			return types;
		}
	}

	public static final class SubjectRws {

		public static final String SUBJECT_RWS_STATUS_SBOPER_TB = "SBOPER_TB";

		public static final String SUBJECT_RWS_STATUS_SBADMIN_SP = "SBADMIN_SP";

		public static final String SUBJECT_RWS_STATUS_ORG_SP = "ORG_SP";

		public static final String SUBJECT_RWS_STATUS_KJS_SP = "KJS_SP";

		public static final String SUBJECT_RWS_STATUS_COMPLETE = "COMPLETE";

		public static final String SUBJECT_RWS_STATUS_BACK = "BACK";

		public static final String SUBJECT_RWS_DOC_TEMPLATE_PATH = "/doc/rws_template.doc";

		public static final String SUBJECT_RWS_DESCRIPTION_NEWS_ID = "402881e42cc658b4012cc663c22f0003";

		public static Map<String, String> getSubjectRwsStatus() {
			Map<String, String> types = new LinkedHashMap<String, String>();
			types.put(SUBJECT_RWS_STATUS_SBOPER_TB, "申报人填报");
			types.put(SUBJECT_RWS_STATUS_SBADMIN_SP, "已提交至本单位管理员审批");
			types.put(SUBJECT_RWS_STATUS_KJS_SP, "已提交至科教司审批");
			types.put(SUBJECT_RWS_STATUS_COMPLETE, "审批通过");
			types.put(SUBJECT_RWS_STATUS_BACK, "已回退");
			return types;
		}

	}

	public static class News {
		/**
		 * 新闻创建状态（取消发布状态）
		 */
		public static final int news_create = 0;
		/**
		 * 新闻发布状态
		 */
		public static final int news_publish = 1;

		/**
		 * 新闻取消发布状态
		 */
		public static final int news_unpublish = -1;
		/**
		 * 通知类型新闻
		 */
		public static final int NOTICE_NEWS = 1;

		/**
		 * 申报类型新闻
		 */
		public static final int SB_NEWS = 2;

		/**
		 * 政策类型新闻
		 */
		public static final int ZC_NEWS = 3;

		/**
		 * 填报类型新闻
		 */
		public static final int TB_NEWS = 4;

		/**
		 * 解析状态值
		 */
		public static Integer[] parseStatus(String status) {
			if (StringUtils.isBlank(status)) {
				return new Integer[] { news_create, news_publish };
			}
			try {
				int parseInt = Integer.parseInt(status);
				return parseInt == news_create ? new Integer[] { news_create }
						: (parseInt == news_publish ? new Integer[] { news_publish }
								: new Integer[] { news_create, news_publish });
			} catch (Exception e) {
				return new Integer[] { news_create, news_publish };
			}
		}

		/**
		 * 解析状态值
		 */
		public static Integer[] parseColumn(String column) {
			if (StringUtils.isBlank(column)) {
				return new Integer[] { NOTICE_NEWS, SB_NEWS, ZC_NEWS, TB_NEWS };
			}

			try {
				int parseInt = (int) Long.parseLong(column);
				switch (parseInt) {
				case NOTICE_NEWS:
					return new Integer[] { NOTICE_NEWS };
				case SB_NEWS:
					return new Integer[] { SB_NEWS };
				case ZC_NEWS:
					return new Integer[] { ZC_NEWS };
				case TB_NEWS:
					return new Integer[] { TB_NEWS };
				default:
					return new Integer[] { NOTICE_NEWS, SB_NEWS, ZC_NEWS, TB_NEWS };
				}
			} catch (Exception e) {
				return new Integer[] { NOTICE_NEWS, SB_NEWS, ZC_NEWS, TB_NEWS };
			}
		}

		/**
		 * 查询所有的栏目
		 */
		public static Map<Integer, String> getColumns() {
			Map<Integer, String> colums = new LinkedHashMap<Integer, String>();
			colums.put(new Integer(NOTICE_NEWS), "通知公告");
			colums.put(new Integer(SB_NEWS), "申报系统和相关文档");
			colums.put(new Integer(ZC_NEWS), "政策法规");
			colums.put(new Integer(TB_NEWS), "填报说明");
			return colums;
		}

		/**
		 * 查询所有状态
		 */
		public static Map<Integer, String> getStatus() {
			Map<Integer, String> colums = new LinkedHashMap<Integer, String>();
			colums.put(news_create, "未发布");
			colums.put(news_publish, "已发布");
			colums.put(news_unpublish, "已取消");
			return colums;
		}

		/**
		 * 查询所有的栏目
		 */
		public static String getStatusName(int status) {
			if (status == news_create) {
				return "未发布";
			}
			if (status == news_publish) {
				return "已发布";
			}
			return "已取消";
		}
	}

	public static final class Org {
		/**
		 * 科教司（国家体育总局）
		 */
		public static final int KJS_ROLE = 0;

		/**
		 * 组织单位（体育中心）
		 */
		public static final int ORG_ROLE = 1;

		/**
		 * 申报单位（注册的单位）
		 */
		public static final int SB_ROLE = 2;

		/**
		 * 等待审核中
		 */
		public static final int wait_verify = 0;
		/**
		 * 审核通过
		 */
		public static final int pass_verify = 1;
		/**
		 * 审核未通过
		 */
		public static final int unpass_verify = -1;

		/**
		 * 解析状态值
		 */
		public static Integer[] parseStatus(String status) {
			if (StringUtils.isBlank(status)) {
				return new Integer[] { wait_verify, pass_verify, unpass_verify };
			}
			try {
				int parseInt = Integer.parseInt(status);
				return parseInt == wait_verify ? new Integer[] { wait_verify }
						: (parseInt == pass_verify ? new Integer[] { pass_verify }
								: (parseInt == unpass_verify ? new Integer[] { unpass_verify }
										: new Integer[] { wait_verify, pass_verify, unpass_verify }));
			} catch (Exception e) {
				return new Integer[] { wait_verify, pass_verify, unpass_verify };
			}
		}

		/**
		 * 查询所有状态
		 */
		public static Map<Integer, String> getStatus() {
			Map<Integer, String> colums = new LinkedHashMap<Integer, String>();
			colums.put(wait_verify, "待审核");
			colums.put(pass_verify, "审核通过");
			colums.put(unpass_verify, "未审核通过");
			return colums;
		}

		/**
		 * 查询状态名称
		 */
		public static String getStatusName(int status) {
			if (status == wait_verify) {
				return "待审核";
			}
			if (status == pass_verify) {
				return "审核通过";
			}
			return "未审核通过";
		}
	}

}