package org.cd.sport.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.News;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.domain.SubjectRwsBudget;
import org.cd.sport.domain.SubjectRwsDevice;
import org.cd.sport.domain.SubjectRwsSchedule;
import org.cd.sport.domain.SubjectRwsUndertaker;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.domain.UserDomain;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.service.DicService;
import org.cd.sport.service.NewsAttachmentService;
import org.cd.sport.view.FileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "SyncDao")
public class SyncDaoImp extends BaseDaoImpl<Subject> implements SyncDao {

	@Autowired
	private DicService dicService;

	@Autowired
	private UserDao userDao;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private SubjectSbsDao subjectSbsDao;

	@Autowired
	private NewsDao newsDao;

	@Autowired
	private NewsAttachmentService newsAttachmentService;

	@Autowired
	private OrganizationDao organizationDao;
	@Autowired
	private SubjectRwsAppropriationDao subjectRwsAppropriationDao;
	@Autowired
	private SubjectRwsBudgetDao subjectRwsBudgetDao;

	@Autowired
	private SubjectRwsDeviceDao subjectRwsDeviceDao;
	@Autowired
	private SubjectRwsScheduleDao subjectRwsScheduleDao;
	@Autowired
	private SubjectRwsUndertakerDao subjectRwsUndertakerDao;

	@Autowired
	private SubjectSbsBudgetDao subjectSbsBudgetDao;

	@Autowired
	private SubjectSbsProposerDao subjectSbsProposerDao;
	private static volatile Connection conn = null;

	public Connection getConn() {
		if (conn != null) {
			return conn;
		}

		String url = "jdbc:mysql://101.200.191.63:3306/finance?"
				+ "user=finance&password=123qweASDZXC&useUnicode=true&characterEncoding=UTF8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	@Transactional
	public void sync() throws SQLException, UnsupportedEncodingException {
//		 this.importUser();
//		 this.importOrg();
//		 this.importNews();
//		this.importRwsAppropriation();
//		 this.importRwsBudget();
//		 this.importRwsDevice();
//		 this.importRwsSchedule();
//		 this.importRwsUndertaker();
		 this.importSbsBudget();
		 this.importSbsProposer();
//		 this.importSubject();
//		 this.importSubjectRws();
//		 this.importSubjectSbs();
	}

	@Transactional
	public void importUser() throws SQLException {
		String sql = "select * from user_info";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.userDao.deleteByLoginName(rs.getString("UI_LOGIN_NAME"));
			UserDomain user = new UserDomain();
			user.setLoginName(rs.getString("UI_LOGIN_NAME"));
			user.setPassword(rs.getString("UI_LOGIN_PASSWORD"));
			user.setUserName(rs.getString("UI_REAL_NAME"));
			user.setGender(rs.getInt("UI_GENDER"));
			user.setCredType(rs.getString("UI_CARD_TYPE"));
			user.setCredNo(rs.getString("UI_CARD_NO"));
			user.setBirthday(rs.getDate("UI_BIRTHDAY"));
			user.setEmail(rs.getString("UI_EMAIL"));
			user.setZc(rs.getString("UI_JOBTITLE"));
			user.setZw(rs.getString("UI_DUTIES"));
			user.setDept(rs.getString("UI_DEPARTMENT"));
			user.setDegrees(rs.getString("UI_EDUCATION"));
			user.setMajor(rs.getString("UI_MAJOR"));
			user.setTelephone(rs.getString("UI_TELEPHONE"));
			user.setPhone(rs.getString("UI_CELLPHONE"));
			user.setAddress(rs.getString("UI_ADDRESS"));
			user.setOrganization(rs.getString("UI_UNITS_ID"));
			int urole = rs.getInt("UI_ROLE");
			if (urole == 1) {
				user.setRole(Constants.Role.ROLE_KJS_LEADER);
			} else if (urole == 2) {
				user.setRole(Constants.Role.ROLE_KJS_EXPERT);
			} else {
				sql = "select * from units_info where U_INFO_UNITSID='" + rs.getString("UI_UNITS_ID") + "'";
				PreparedStatement ps2 = getConn().prepareStatement(sql);
				ResultSet rs2 = ps2.executeQuery();
				while (rs2.next()) {
					int role = rs2.getInt("U_INFO_UNITS_ROLE");
					if (role == 0) {
						if (urole == 0) {
							user.setRole(Constants.Role.ROLE_KJS_ADMIN);
						}
					} else if (role == 2) {
						if (urole == 0) {
							user.setRole(Constants.Role.ROLE_SB_ADMIN);
						} else if (urole == 3) {
							user.setRole(Constants.Role.ROLE_SB_OPER);
						}
					} else if (role == 1) {
						if (urole == 0) {
							user.setRole(Constants.Role.ROLE_ORG_ADMIN);
						} else if (urole == 3) {
							user.setRole(Constants.Role.ROLE_ORG_OPER);
						}
					}
				}
			}
			userDao.save(user);
		}

	}

	@Transactional
	public void importOrg() throws SQLException {
		// 组织单位
		String sql = "select * from units_info";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			organizationDao.delete(rs.getString("U_INFO_UNITSID"));
			OrganizationDomain subject = new OrganizationDomain();
			subject.setOrgId(rs.getString("U_INFO_UNITSID"));
			subject.setHomepage(rs.getString("U_INFO_UNITS_HOMEPAGE"));
			subject.setFullName(rs.getString("U_INFO_UNITSNAME"));
			subject.setEnglishName(rs.getString("U_INFO_EN_NAME"));
			subject.setShortName(rs.getString("U_INFO_UNITS_SHORT_NAME"));
			subject.setAddress(rs.getString("U_INFO_UNITS_ADDRESS"));
			subject.setLegalLeader(rs.getString("U_INFO_LEGAL_REP"));
			subject.setRegion(rs.getString("U_INFO_REGION"));
			subject.setQuality(rs.getString("U_INFO_TYPE"));
			String code = rs.getString("U_INFO_CODE_OF_OGNI");
			subject.setCode(code);
			subject.setTelphone(rs.getString("U_INFO_UNITS_PHONE"));
			subject.setFax(rs.getString("U_INFO_UNITS_FAX"));
			subject.setEmail(rs.getString("U_INFO_UNITS_EMAIL"));
			subject.setPost(rs.getString("U_INFO_UNITS_ZIPCODE"));
			subject.setManagerName(rs.getString("U_INFO_PRINCIPAL_NAME"));
			subject.setManagerPhone(rs.getString("U_INFO_PRINCIPAL_CELLPHONE"));
			subject.setManagerTel(rs.getString("U_INFO_PRINCIPAL_PHONE"));
			subject.setManagerfax(rs.getString("U_INFO_PRINCIPAL_FAX"));
			subject.setManagerEmail(rs.getString("U_INFO_PRINCIPAL_EMAIL"));
			subject.setRole(rs.getInt("U_INFO_UNITS_ROLE"));
			organizationDao.save(subject);
			int status = rs.getInt("U_INFO_UNITS_STATE");
			subject.setStatus(status);
		}
	}

	@Transactional
	public void importNews() throws SQLException, UnsupportedEncodingException {
		String sql = "select * from articles_info";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			newsDao.deleteById(new String[] { rs.getString("AI_ID") });
			News subject = new News();
			subject.setId(rs.getString("AI_ID"));
			String c = rs.getString("AI_BELONG_PROGRAMA");
			c = c.substring(c.length() - 1, c.length());
			subject.setColumnId(Integer.parseInt(c));
			subject.setTitle(rs.getString("AI_TITLE"));
			subject.setContent(rs.getString("AI_CONTENT").getBytes("UTF-8"));
			subject.setPublishTime(rs.getDate("AI_PUBLISH_TIME"));
			subject.setStatus(rs.getInt("AI_STATE"));
			newsDao.save(subject);

			sql = "select * from articles_accessory where AA_ARTICLES_ID='" + rs.getString("AI_ID") + "'";
			PreparedStatement pss = getConn().prepareStatement(sql);
			ResultSet rss = pss.executeQuery();
			List<FileView> files = new ArrayList<FileView>();
			newsAttachmentService.deleteByNewsId(rs.getString("AI_ID"));
			while (rss.next()) {
				FileView file = new FileView();
				file.setId(rss.getString("AA_ID"));
				file.setName(rss.getString("AA_NAME"));
				file.setPath(rss.getString("AA_FILE_NAME"));
				files.add(file);
			}
			newsAttachmentService.create(rs.getString("AI_ID"), files);
		}
	}

	@Transactional
	public void importSubject() throws SQLException {
		String sql = "select * from subject_info";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectDao.deleteSubjectById(rs.getString("SI_ID"));
			Subject subject = new Subject();
			subject.setId(rs.getString("SI_ID"));
			subject.setNum(rs.getString("SI_CODE"));
			subject.setName(rs.getString("SI_NAME"));
			// 1 国家SUBJECT_TYPE_KYGGKT
			int type = rs.getInt("SI_TYPE");
			if (type == 1) {
				subject.setType(Constants.Subject.SUBJECT_TYPE_KYGGKT);
			} else if (type == 0) {
				subject.setType(Constants.Subject.SUBJECT_TYPE_ZBKT);
			}
			subject.setOrganizationId(rs.getString("SI_OGNI_UNITS_ID"));
			OrganizationDomain org = organizationDao.getEntityById(OrganizationDomain.class,
					(rs.getString("SI_OGNI_UNITS_ID")));
			if (org != null) {
				subject.setOrganizationName(org.getFullName());
			}
			subject.setSecurity(rs.getString("SI_SECRET_GRADE"));
			Dic dic = dicService.getByCode(rs.getString("SI_SECRET_GRADE"));
			if (dic != null) {
				subject.setSecurityName(dic.getName());
			}
			subject.setBeginDate(rs.getDate("SI_START_DATE"));
			subject.setEndDate(rs.getDate("SI_END_DATE"));
			subject.setResults(rs.getString("SI_ACHIVEMENT"));
			if (StringUtils.isNotBlank(subject.getResults())) {
				String resultsName = "";
				for (String code : subject.getResults().split(",")) {
					dic = dicService.getByCode(code);
					resultsName += dic.getName() + ",";
				}
				resultsName = resultsName.substring(0, resultsName.length() - 1);
				subject.setResultsName(resultsName);
			}
			subject.setIntegration(rs.getBoolean("SI_COOP_PSR"));
			subject.setCreateUnitId(rs.getString("SI_UNDERTAKE_UNITS_ID"));
			subject.setCreateUnitName(rs.getString("SI_UNDERTAKE_UNITS_NAME"));
			subject.setCreatorName(rs.getString("SI_PRINCIPAL_NAME"));
			subject.setCreator(rs.getString("SI_PRINCIPAL_LOGIN_NAME"));
			subject.setOrganizationCount(rs.getString("SI_UNIT_COUNT"));
			subject.setCreateTime(rs.getDate("SI_CREAT_TIME"));
			subject.setSbsEndDate(rs.getDate("SI_TENDER_END_TIME"));
			subject.setRwsEndDate(rs.getDate("SI_TASK_END_TIME"));
			subject.setSubjectEndDate(rs.getDate("SI_OVER_END_TIME"));
			subject.setNewState(rs.getInt("NEW_STATE"));
			// 状态值处理
			int state = rs.getInt("SI_STATE");
			if (state == 0) {
				subject.setStage(Constants.Subject.SUBJECT_STAGE_SBSTB);
			} else if (state == 1) {
				subject.setStage(Constants.Subject.SUBJECT_STAGE_RWSTB);
			} else if (state == 2) {
				subject.setStage(Constants.Subject.SUBJECT_STAGE_JDBG);
			} else if (state == 3) {
				subject.setStage(Constants.Subject.SUBJECT_STAGE_JTBG);
			} else if (state == 9) {
				subject.setStage(Constants.Subject.SUBJECT_STAGE_JTBACK);
			}
			this.subjectDao.save(subject);
		}
	}

	@Transactional
	public void importSubjectSbs() throws SQLException {
		String sql = "select * from subject_declaration";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectSbsDao.deleteById(rs.getString("SD_ID"));
			SubjectSbs subject = new SubjectSbs();
			subject.setSbsId(rs.getString("SD_ID"));
			subject.setSubjectId(rs.getString("SD_SUBJECT_ID"));
			subject.setAddress(rs.getString("SD_MESSAGE_ADDRESS"));
			subject.setPhone(rs.getString("SD_PHONE"));
			subject.setFax(rs.getString("SD_TAX"));
			subject.setEmail(rs.getString("SD_EMAIL"));
			subject.setYears(rs.getString("SD_END_YEAR"));
			// 状态值处理
			int state = rs.getInt("SD_STATE");
			if (state == 0) {
				subject.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_SBOPER_TB);
			} else if (state == 1) {
				subject.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_SBADMIN_SP);
			} else if (state == 2) {
				subject.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_ORG_SP);
			} else if (state == 3) {
				subject.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_KJS_SP);
			} else if (state == 8) {
				subject.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_COMPLETE);
			} else if (state == 9) {
				subject.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_BACK);
			}
			// 回退意见处理 TODO
			String advice = rs.getString("SD_RETURN_ADVICE");
			subject.setComment(advice);
			this.subjectSbsDao.save(subject);
		}
	}

	@Transactional
	public void importSubjectRws() throws SQLException { // RWS_ADDRESS
		String sql = "select * from subject_task_book";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectSbsDao.deleteById(rs.getString("ID"));
			SubjectRws subject = new SubjectRws();
			subject.setRwsId(rs.getString("ID"));
			subject.setSubjectId(rs.getString("STB_SUBJECT_ID"));
			subject.setAddress(rs.getString("STB_MESSAGE_ADDRESS"));
			subject.setPhone(rs.getString("STB_PHONE"));
			subject.setStartDate(rs.getDate("STB_START_DATE"));
			subject.setEndDate(rs.getDate("STB_END_DATE"));
			subject.setCooperateOrg(rs.getString("STB_COOPERATION_UNIT"));

			// 状态值处理
			int state = rs.getInt("STB_STATE");
			if (state == 0) {
				subject.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_SBOPER_TB);
			} else if (state == 1) {
				subject.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_SBADMIN_SP);
			} else if (state == 2) {
				subject.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_ORG_SP);
			} else if (state == 3) {
				subject.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_KJS_SP);
			} else if (state == 8) {
				subject.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_COMPLETE);
			} else if (state == 9) {
				subject.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_BACK);
			}
			// 回退意见处理 TODO
			String advice = rs.getString("STB_RETURN_ADVISE");
			subject.setComment(advice);
			this.subjectSbsDao.save(subject);
		}
	}

	/**
	 * 进度安排
	 * 
	 * @throws SQLException
	 */
	@Transactional
	public void importRwsSchedule() throws SQLException { // RWS_ADDRESS
		String sql = "select * from task_scheduling t left join subject_task_book b ON t.TS_SUBJECT_TASK_ID=b.ID";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectRwsScheduleDao.deleteById(rs.getString("ID"));
			SubjectRwsSchedule subject = new SubjectRwsSchedule();
			subject.setsId(rs.getString("ID"));
			subject.setRwsId(rs.getString("TS_SUBJECT_TASK_ID"));
			subject.setSubjectId(rs.getString("STB_SUBJECT_ID"));
			subject.setYear(Integer.parseInt(rs.getString("STB_YEAR")));
			subject.setWork(rs.getString("STB_MAJOR_WORK_CONTENT"));
			subject.setMonth(Integer.parseInt(rs.getString("STB_MONTH")));
			subject.setGoal(rs.getString("STB_TARGET"));
			this.subjectRwsScheduleDao.save(subject);
		}
	}

	/**
	 * 责任人
	 * 
	 * @throws SQLException
	 */
	@Transactional
	public void importRwsUndertaker() throws SQLException { // RWS_ADDRESS
		String sql = "select * from project_person t left join subject_task_book b ON t.PP_SUBJECT_TASK_ID=b.ID";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectRwsUndertakerDao.deleteById(rs.getString("ID"));
			SubjectRwsUndertaker subject = new SubjectRwsUndertaker();
			subject.setId(rs.getString("ID"));
			subject.setRwsId(rs.getString("PP_SUBJECT_TASK_ID"));
			subject.setSubjectId(rs.getString("STB_SUBJECT_ID"));
			subject.setWork(rs.getString("PP_INSTITUTE_WORK"));
			String age = rs.getString("PP_AGE");
			subject.setAge(StringUtils.isBlank(age) ? 0 : Integer.parseInt(age));
			subject.setName(rs.getString("PP_NAME"));
			subject.setOrg(rs.getString("PP_UNITS"));
			subject.setDegrees(rs.getString("PP_EDUCATION"));
			subject.setType(rs.getString("PP_TYPE"));
			subject.setMajor(rs.getString("PP_MAJOR"));
			subject.setZw(rs.getString("PP_DUTY"));
			this.subjectRwsUndertakerDao.save(subject);
		}

	}

	/**
	 * 设备资源
	 * 
	 * @throws SQLException
	 */
	@Transactional
	public void importRwsDevice() throws SQLException {
		String sql = "select * from equipment_acquisition_detail t left join subject_task_book b ON t.EAD_SUBJECT_TASK_ID=b.ID";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectRwsDeviceDao.deleteById(rs.getString("ID"));
			SubjectRwsDevice subject = new SubjectRwsDevice();
			subject.setdId(rs.getString("ID"));
			subject.setRwsId(rs.getString("EAD_SUBJECT_TASK_ID"));
			subject.setSubjectId(rs.getString("STB_SUBJECT_ID"));
			subject.setName(rs.getString("EAD_EQUIPMENT_NAME"));
			subject.setPurpose(rs.getString("EAD_WHY_HOW_USE"));
			subject.setNorm(rs.getString("EAD_MODEL_PER"));
			subject.setPrice(rs.getBigDecimal("EAD_UNIT_PRICE"));
			String age = rs.getString("EAD_COUNT");
			subject.setNum(StringUtils.isBlank(age) ? 0 : Integer.parseInt(age));
			subject.setBuy(rs.getString("EAD_ACQUISITION"));
			subject.setOrgin(rs.getString("EAD_EQUIP_COUNTRY_REGION"));
			subject.setSlzs(rs.getString("EAD_APPLY_MONEY"));
			this.subjectRwsDeviceDao.save(subject);
		}
	}

	/**
	 * 预算
	 * 
	 * @throws SQLException
	 */
	@Transactional
	public void importRwsBudget() throws SQLException {
		String sql = "select * from task_funds_budget t";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectRwsBudgetDao.deleteById(rs.getString("TFB_SUBJECT_TASK_ID"),
					rs.getString("TFB_APPLY_OPTION_CODE"));
			SubjectRwsBudget subject = new SubjectRwsBudget();
			// subject.setCode(rs.getString("ID"));
			// subject.setRwsId(rs.getString("TFB_SUBJECT_TASK_ID"));
			// subject.setCost(rs.getBigDecimal("TFB2_MONEY"));
			// subject.setName(rs.getString("TFB2_TYPE"));
			// String reason = rs.getString("TFB2_MEMO");
			// subject.setReason(rs.getString(StringUtils.isBlank(reason) ? null
			// : reason));
			subject.setCode(rs.getString("TFB_APPLY_OPTION_CODE"));
			subject.setRwsId(rs.getString("TFB_SUBJECT_TASK_ID"));
			subject.setCost(rs.getBigDecimal("TFB_MONEY"));
			subject.setName(rs.getString("TFB_TYPE"));
			subject.setReason(rs.getString("TFB_MEMO"));
			this.subjectRwsBudgetDao.save(subject);
		}

		// CREATE TABLE `task_funds_budget` (
		// `ID` varchar(32) NOT NULL,
		// `TFB_SUBJECT_TASK_ID` varchar(32) NOT NULL,
		// `TFB_TYPE` varchar(50) NOT NULL,
		// `TFB_MONEY` decimal(9,2) NOT NULL,
		// `TFB_UNIT_PRICE` decimal(9,2) DEFAULT NULL,
		// `TFB_TIMES` int(11) DEFAULT NULL,
		// `TFB_COUNT` decimal(9,2) DEFAULT NULL,
		// `TFB_COUNT_ACCORD_REASON` varchar(100) DEFAULT NULL,
		// `TFB_MEMO` varchar(200) DEFAULT NULL,
		// `TFB_FORMULA` varchar(500) DEFAULT NULL,
		// `TFB_IF_ADD_ACCORD` varchar(100) DEFAULT NULL,
		// `TFB_APPLY_OPTION_CODE` varchar(32) DEFAULT NULL,
		// `TFB_MONEY_IF_RON` varchar(100) DEFAULT NULL,
		// PRIMARY KEY (`ID`)
		// ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
	}

	/**
	 * 拨往其他单位的预算
	 * 
	 * @throws SQLException
	 */
	public void importRwsAppropriation() throws SQLException {
		String sql = "select * from to_other_unit_funds t left join subject_task_book b ON t.TOUF_SUBJECT_TASK_ID=b.ID";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			subjectRwsAppropriationDao.deleteById(rs.getString("ID"));
			SubjectRwsAppropriation subject = new SubjectRwsAppropriation();
			subject.setApproId(rs.getString("ID"));
			subject.setSubjectId(rs.getString("STB_SUBJECT_ID"));
			subject.setRwsId(rs.getString("TOUF_SUBJECT_TASK_ID"));
			subject.setApproAmount(rs.getBigDecimal("TOUF_TO_MONEY"));
			subject.setGainOrg(rs.getString("TOUF_TO_UNIT"));
			String describe = rs.getString("TOUF_DO_WHAT");
			subject.setDescribe(describe);
			this.subjectRwsAppropriationDao.save(subject);
		}
	}

	/**
	 * 申报书预算
	 * 
	 * @throws SQLException
	 */
	@Transactional
	public void importSbsBudget() throws SQLException {
		String sql = "select * from funds_budget";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectSbsBudgetDao.deleteById(rs.getString("FB_DECLARATION_ID"),
					rs.getString("FB_APPLY_OPTION_CODE"));
			SubjectSbsBudget subject = new SubjectSbsBudget();
			// subject.setSubjectId(rs.getString("SD_SUBJECT_ID"));
			subject.setCode(rs.getString("FB_APPLY_OPTION_CODE"));
			subject.setSbsId(rs.getString("FB_DECLARATION_ID"));
			subject.setCost(rs.getBigDecimal("FB_MONEY"));
			subject.setName(rs.getString("FB_TYPE"));
			subject.setReason(rs.getString("FB_COUNT_ACCORD_REASON"));
			this.subjectSbsBudgetDao.save(subject);
		}
	}

	@Transactional
	public void importSbsProposer() throws SQLException {
		// 主要申请人
		String sql = "select * from major_declarant_info t left join subject_declaration d on t.MDI_DECLARATION_ID=d.SD_SUBJECT_ID";
		PreparedStatement ps = getConn().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			this.subjectSbsProposerDao.deleteById(rs.getString("ID"));
			SubjectSbsProposer subject = new SubjectSbsProposer();
			subject.setSubjectId(rs.getString("SD_SUBJECT_ID"));
			subject.setId(rs.getString("ID"));
			subject.setSbsId(rs.getString("MDI_DECLARATION_ID"));
			subject.setPrimary(Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_PRIMARY);
			subject.setBirthday(rs.getDate("MDI_BIRTHDAY"));
			subject.setMajor(rs.getString("MDI_MAJOR"));
			subject.setOrg(rs.getString("MDI_UNITS"));
			subject.setGender(rs.getInt("MDI_GENDER"));
			subject.setName(rs.getString("MDI_NAME"));
			subject.setZw(rs.getString("MDI_DUTY"));
			subject.setDegrees(rs.getString("MDI_DEGREE"));
			subject.setEmail(rs.getString("MDI_EMAIL"));
			subject.setPhone(rs.getString("MDI_PHONE"));
			subject.setBackdrop(rs.getString("MDI_DESCRIPTION"));
			subject.setUniversity(rs.getString("MDI_GRADUATE_SCHOOL"));
			subject.setWork(rs.getString("MDI_INSTITUTE_WORK"));
			subject.setXuewei(rs.getString("MDI_EDUCATION"));
			subject.setSort(rs.getInt("MDI_WHICH_DECLA"));
			this.subjectSbsProposerDao.save(subject);
		}

		// 其他申请人
		sql = "select * from other_declarant_info t left join subject_declaration d on t.ODI_DECLARATION_ID=d.SD_SUBJECT_ID";
		PreparedStatement pss = getConn().prepareStatement(sql);
		ResultSet rss = pss.executeQuery();
		while (rss.next()) {
			this.subjectSbsProposerDao.deleteById(rss.getString("ID"));
			SubjectSbsProposer subject = new SubjectSbsProposer();
			subject.setSubjectId(rss.getString("SD_SUBJECT_ID"));
			subject.setId(rss.getString("ID"));
			subject.setSbsId(rss.getString("ODI_DECLARATION_ID"));
			subject.setPrimary(Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_OTHER);
			subject.setMajor(rss.getString("ODI_MAJOR"));
			subject.setOrg(rss.getString("ODI_UNITS"));
			subject.setName(rss.getString("ODI_NAME"));
			subject.setZw(rss.getString("ODI_DUTY"));
			subject.setDegrees(rss.getString("ODI_DEGREE"));
			subject.setWork(rss.getString("ODI_INSTITUTE_WORK"));
			subject.setXuewei(rss.getString("ODI_EDUCATION"));
			String age = rss.getString("ODI_AGE");
			Date bir = rss.getDate("SD_DECLA_TIME");
			if (bir != null && StringUtils.isBlank(age)) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(bir);
				calendar.add(Calendar.YEAR, -Integer.parseInt(age));
				subject.setBirthday(new Date(calendar.getTime().getTime()));
			}
			this.subjectSbsProposerDao.save(subject);
		}
	}
}
