package org.cd.sport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectDao;
import org.cd.sport.dao.SubjectRwsDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectRws;
import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.domain.SubjectRwsBudget;
import org.cd.sport.domain.SubjectRwsSchedule;
import org.cd.sport.vo.SubjectRwsUndertakerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectRwsServiceImpl implements SubjectRwsService {

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectRwsBudgetService subjectRwsBudgetService;

	@Autowired
	private SubjectRwsScheduleService subjectRwsScheduleService;

	@Autowired
	private SubjectRwsUndertakerService subjectRwsUndertakerService;

	@Autowired
	private SubjectRwsAppropriationService subjectRwsAppropriationService;

	@Autowired
	private SubjectRwsDao subjectRwsDao;

	@Autowired
	private SubjectDao subjectDao;

	@Override
	public SubjectRws getSubjectRwsById(String id) {
		return this.subjectRwsDao.getEntityById(SubjectRws.class, id);
	}

	@Override
	public SubjectRws getRwsBySubjectId(String subjectId) {
		return this.getOrCreateSubjectRws(subjectId);
	}

	@Override
	public SubjectRws createSubjectRws(String subjectId) {
		SubjectRws rws = new SubjectRws();
		rws.setSubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_SBOPER_TB);
		this.subjectRwsDao.save(rws);
		return rws;
	}

	private SubjectRws getOrCreateSubjectRws(String subjectId) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		if (rws == null) {
			return this.createSubjectRws(subjectId);
		} else {
			return rws;
		}
	}

	@Override
	public void saveBaseInfo(String subjectId, String address, String phone, String cooperateOrg) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setAddress(address);
		rws.setPhone(phone);
		rws.setCooperateOrg(cooperateOrg);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYjmb(String subjectId, String yjmb) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setYjmb(yjmb);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveJsgj(String subjectId, String jsgj) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setJsgj(jsgj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYjff(String subjectId, String yjff) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setYjff(yjff);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveSyfa(String subjectId, String syfa) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setSyfa(syfa);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveYqjg(String subjectId, String yqjg) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setYqjg(yqjg);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void saveGztj(String subjectId, String gztj) {
		SubjectRws rws = getOrCreateSubjectRws(subjectId);
		rws.setGztj(gztj);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public Map<String, String> checkAndSubmit(String subjectId, String basePath) {
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectRws rws = this.getRwsBySubjectId(subjectId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		if (rws == null) {
			Map<String, String> result = new HashMap<String, String>();
			result.put("success", "false");
			result.put("msg", "申报书尚未填报");
			return result;
		}

		boolean complete = true;
		StringBuilder msg = new StringBuilder("");
		if (rws.getAddress() == null || rws.getAddress().equals("")) {
			msg.append("通讯地址未填写<br/>");
			complete = false;
		}
		if (rws.getPhone() == null || rws.getPhone().equals("")) {
			msg.append("联系电话未填写<br/>");
			complete = false;
		}
		if (rws.getYjmb() == null || rws.getYjmb().equals("")) {
			msg.append("研究目标和研究内容未填写<br/>");
			complete = false;
		}
		if (rws.getJsgj() == null || rws.getJsgj().equals("")) {
			msg.append("技术关键和创新点未填写<br/>");
			complete = false;
		}
		if (rws.getYjff() == null || rws.getYjff().equals("")) {
			msg.append("采取的研究方法、主要技术路线、主要指标及可行性分析未填写<br/>");
			complete = false;
		}
		if (rws.getSyfa() == null || rws.getSyfa().equals("")) {
			msg.append("研究实验方案、实验地点及协作单位的分工<br/>");
			complete = false;
		}
		if (rws.getYqjg() == null || rws.getYqjg().equals("")) {
			msg.append("预期结果未填写<br/>");
			complete = false;
		}
		if (rws.getGztj() == null || rws.getGztj().equals("")) {
			msg.append("承担单位现有工作条件和基础未填写<br/>");
			complete = false;
		}
		if (rws.getCooperateOrg() == null || rws.getCooperateOrg().equals("")) {
			msg.append("协作单位未填写<br/>");
			complete = false;
		}
		List<SubjectRwsBudget> budgets = subjectRwsBudgetService.getByRwsId(rws.getRwsId());
		if (budgets == null || budgets.isEmpty()) {
			msg.append("经费预算未填写<br/>");
			complete = false;
		}
		List<SubjectRwsSchedule> schedules = subjectRwsScheduleService.getByRwsId(rws.getRwsId());
		if (schedules == null || schedules.isEmpty()) {
			msg.append("进度安排未填写<br/>");
			complete = false;
		}
		List<SubjectRwsUndertakerVo> undertakers = subjectRwsUndertakerService.getByRwsId(rws.getRwsId());
		if (undertakers == null || undertakers.isEmpty()) {
			msg.append("承担单位、协作单位和人员情况未填写<br/>");
			complete = false;
		}

		if (complete == false) {
			Map<String, String> result = new HashMap<String, String>();
			result.put("success", "false");
			result.put("msg", msg.toString());
			return result;
		}

		FileInputStream in = null;
		OutputStream os = null;
		try {
			in = new FileInputStream(new File(basePath + Constants.SubjectRws.SUBJECT_RWS_DOC_TEMPLATE_PATH));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();
			range.replaceText("${subjectName}", subject.getName());
			range.replaceText("${createUnitName}", subject.getCreateUnitName());
			range.replaceText("${organizationName}", subject.getOrganizationName());
			range.replaceText("${creatorName}", subject.getCreatorName());
			range.replaceText("${address}", rws.getAddress());
			range.replaceText("${phone}", rws.getPhone());
			Date beginDate = subject.getBeginDate();
			range.replaceText("${beginDate}", beginDate != null ? sdf.format(beginDate) : "");
			Date endDate = subject.getEndDate();
			range.replaceText("${endDate}", endDate != null ? sdf.format(endDate) : "");
			range.replaceText("${yjmb}", rws.getYjmb());
			range.replaceText("${jsgj}", rws.getJsgj());
			range.replaceText("${yjff}", rws.getYjff());
			range.replaceText("${syfa}", rws.getSyfa());
			range.replaceText("${yqjg}", rws.getYqjg());
			range.replaceText("${gztj}", rws.getGztj());

			for (int i = 0; i < 15; i++) {
				if (i < schedules.size()) {
					SubjectRwsSchedule schedule = schedules.get(i);
					range.replaceText("${year" + i + "}", String.valueOf(schedule.getYear()));
					range.replaceText("${month" + i + "}", String.valueOf(schedule.getMonth()));
					range.replaceText("${work" + i + "}", String.valueOf(schedule.getWork()));
					range.replaceText("${goal" + i + "}", String.valueOf(schedule.getGoal()));
				} else {
					range.replaceText("${year" + i + "}", "");
					range.replaceText("${month" + i + "}", "");
					range.replaceText("${work" + i + "}", "");
					range.replaceText("${goal" + i + "}", "");
				}
			}

			for (int i = 0; i < 20; i++) {
				if (i < undertakers.size()) {
					SubjectRwsUndertakerVo user = undertakers.get(i);
					range.replaceText("${oxm" + i + "}", user.getName());
					range.replaceText("${odw" + i + "}", user.getOrg());
					range.replaceText("${onl" + i + "}", user.getAge());
					range.replaceText("${ozw" + i + "}", user.getZw());
					range.replaceText("${oxl" + i + "}", user.getDegrees());
					range.replaceText("${ozy" + i + "}", user.getMajor());
					range.replaceText("${ofg" + i + "}", user.getWork());
				} else {
					range.replaceText("${oxm" + i + "}", "");
					range.replaceText("${odw" + i + "}", "");
					range.replaceText("${onl" + i + "}", "");
					range.replaceText("${ozw" + i + "}", "");
					range.replaceText("${oxl" + i + "}", "");
					range.replaceText("${ozy" + i + "}", "");
					range.replaceText("${ofg" + i + "}", "");
				}
			}

			List<SubjectRwsAppropriation> appropriations = subjectRwsAppropriationService.getByRwsId(rws.getRwsId());
			for (int i = 0; i < 10; i++) {
				if (i < appropriations.size()) {
					SubjectRwsAppropriation appropriation = appropriations.get(i);
					range.replaceText("${adw" + i + "}", appropriation.getGainOrg());
					range.replaceText("${ase" + i + "}", String.valueOf(appropriation.getApproAmount()));
					range.replaceText("${ayt" + i + "}", appropriation.getDescribe());
				} else {
					range.replaceText("${adw" + i + "}", "");
					range.replaceText("${ase" + i + "}", "");
					range.replaceText("${ayt" + i + "}", "");
				}
			}

			// 填充预算信息
			for (SubjectRwsBudget budget : budgets) {
				range.replaceText("${D_" + budget.getCode().substring(3) + "}",
						budget.getReason() == null ? "" : budget.getReason());
			}

			os = new FileOutputStream(basePath + "/doc/rws_" + subject.getId() + subject.getCreator() + ".doc");
			hdt.write(os);

			rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_SBADMIN_SP);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				in.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("success", "true");
		return result;
	}

	@Override
	public void sbadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_ZBKT)) {
			rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_KJS_SP);
			this.subjectRwsDao.update(rws);
		} else if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_KYGGKT)) {
			rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_ORG_SP);
			this.subjectRwsDao.update(rws);
		}
	}

	@Override
	public void sbadminUnpass(String subjectId, String comment) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_BACK);
		rws.setComment(comment);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void orgadminPass(String subjectId) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_KJS_SP);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void orgadminUnpass(String subjectId, String comment) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_BACK);
		rws.setComment(comment);
		this.subjectRwsDao.update(rws);
	}

	@Override
	public void kjsadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_COMPLETE);
		this.subjectRwsDao.update(rws);
		subject.setStage(Constants.Subject.SUBJECT_STAGE_JTBG);
		this.subjectDao.save(subject);
	}

	@Override
	public void kjsadminUnpass(String subjectId, String comment) {
		SubjectRws rws = this.subjectRwsDao.getRwsBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectRws.SUBJECT_RWS_STATUS_BACK);
		rws.setComment(comment);
		this.subjectRwsDao.update(rws);
	}

}
