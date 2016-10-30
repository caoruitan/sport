package org.cd.sport.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.hibernate.BaseDaoImpl;
import org.cd.sport.service.DicService;
import org.cd.sport.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "SyncDao")
public class SyncDaoImp extends BaseDaoImpl<Subject> implements SyncDao {

	@Autowired
	private DicService dicService;

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private SubjectSbsDao subjectSbsDao;

	@Autowired
	private OrganizationService organizationService;

	public Connection getConn() {
		Connection conn = null;
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
			OrganizationDomain org = organizationService.getById(rs.getString("SI_OGNI_UNITS_ID"));
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
			this.subjectSbsDao.save(subject);
		}

		// ID
		// STB_SUBJECT_ID
		// STB_REPORTOR_LOGIN_NAME
		// STB_SUBJECT_CODE
		// STB_SUBJECT_NAME
		// STB_OGNI_UNITS_NAME
		// STB_UNDERTAKE_UNITS_NAME
		// STB_PRINCIPAL_NAME
		// STB_MESSAGE_ADDRESS
		// STB_PHONE
		// STB_START_DATE
		// STB_END_DATE
		// STB_COOPERATION_UNIT
		// STB_CHECK_FLAG
		// STB_STATE
		// STB_SUBMIT_TIME
		// STB_RETURN_ADVISE
	}

	public static void main(String[] args) throws SQLException {
		new SyncDaoImp().importSubject();
	}
}
