package org.cd.sport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectDao;
import org.cd.sport.dao.SubjectSbsDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.vo.SubjectSbsProposerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectSbsServiceImpl implements SubjectSbsService {
	
	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SubjectSbsProposerService subjectSbsProposerService;
	
	@Autowired
	private SubjectSbsBudgetService subjectSbsBudgetService;

	@Autowired
	private SubjectSbsDao subjectSbsDao;

	@Autowired
	private SubjectDao subjectDao;

	@Override
	public SubjectSbs getSubjectSbsById(String id) {
		return this.subjectSbsDao.getEntityById(SubjectSbs.class, id);
	}
	
	@Override
	public SubjectSbs getSbsBySubjectId(String subjectId) {
		return this.subjectSbsDao.getSbsBySubjectId(subjectId);
	}

	@Override
	public synchronized SubjectSbs createSubjectSbs(String subjectId) {
		SubjectSbs sbs = new SubjectSbs();
		sbs.setSubjectId(subjectId);
		sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_SBOPER_TB);
		this.subjectSbsDao.save(sbs);
		return sbs;
	}
	
	private SubjectSbs getOrCreateSubjectSbs(String subjectId) {
		SubjectSbs sbs = this.getSbsBySubjectId(subjectId);
		if(sbs == null) {
			return this.createSubjectSbs(subjectId);
		} else {
			return sbs;
		}
	}
	
	@Override
	public void saveBaseInfo(String subjectId, String address, String phone, String fax, String email, String years) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setAddress(address);
		sbs.setPhone(phone);
		sbs.setFax(fax);
		sbs.setEmail(email);
		sbs.setYears(years);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveXtyj(String subjectId, String xtyj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setXtyj(xtyj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveYjmb(String subjectId, String yjmb) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setYjmb(yjmb);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveJsgj(String subjectId, String jsgj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setJsgj(jsgj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveYjff(String subjectId, String yjff) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setYjff(yjff);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveSyfa(String subjectId, String syfa) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setSyfa(syfa);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveJdap(String subjectId, String jdap) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setJdap(jdap);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveYqjg(String subjectId, String yqjg) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setYqjg(yqjg);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveGztj(String subjectId, String gztj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setGztj(gztj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void saveTjyj(String subjectId, String tjyj) {
		SubjectSbs sbs = getOrCreateSubjectSbs(subjectId);
		sbs.setTjyj(tjyj);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public Map<String, String> checkAndSubmit(String subjectId, String basePath) {
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectSbs sbs = this.getSbsBySubjectId(subjectId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		if(sbs == null) {
			Map<String, String> result = new HashMap<String, String>();
			result.put("success", "false");
			result.put("msg", "申报书尚未填报");
			return result;
		}
		
		boolean complete = true;
		StringBuilder msg = new StringBuilder("");
		// 验证基本信息是否完整
		if(sbs.getPhone() == null || sbs.getPhone().equals("")) {
			msg.append("联系方式未填写<br/>");
			complete = false;
		}
		if(sbs.getXtyj() == null || sbs.getXtyj().equals("")) {
			msg.append("选题依据未填写<br/>");
			complete = false;
		}
		if(sbs.getYjmb() == null || sbs.getYjmb().equals("")) {
			msg.append("研究目标和主要研究内容未填写<br/>");
			complete = false;
		}
		if(sbs.getJsgj() == null || sbs.getJsgj().equals("")) {
			msg.append("技术关键和创新点未填写<br/>");
			complete = false;
		}
		if(sbs.getYjff() == null || sbs.getYjff().equals("")) {
			msg.append("拟采取的研究方法、主要技术路线、主要指标及可行性分析未填写<br/>");
			complete = false;
		}
		if(sbs.getSyfa() == null || sbs.getSyfa().equals("")) {
			msg.append("研究实验方案、实验地点及联合申请单位的分工未填写<br/>");
			complete = false;
		}
		if(sbs.getJdap() == null || sbs.getJdap().equals("")) {
			msg.append("进度安排未填写<br/>");
			complete = false;
		}
		if(sbs.getYqjg() == null || sbs.getYqjg().equals("")) {
			msg.append("预期结果未填写<br/>");
			complete = false;
		}
		if(sbs.getGztj() == null || sbs.getGztj().equals("")) {
			msg.append("申报单位现有工作条件和基础未填写<br/>");
			complete = false;
		}
		List<SubjectSbsProposerVo> primaryProposers = subjectSbsProposerService.getBySbsId(sbs.getSbsId(), Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_PRIMARY);
		if(primaryProposers == null || primaryProposers.isEmpty()) {
			msg.append("申请人员情况未填写<br/>");
			complete = false;
		}
		List<SubjectSbsBudget> budgets = subjectSbsBudgetService.getBySbsId(sbs.getSbsId());
		if(budgets == null || budgets.isEmpty()) {
			msg.append("经费预算未填写<br/>");
			complete = false;
		}
		if(sbs.getTjyj() == null || sbs.getTjyj().equals("")) {
			msg.append("申报单位推荐意见及提供相关研究工作条件的保证未填写<br/>");
			complete = false;
		}
		
		if(complete == false) {
			Map<String, String> result = new HashMap<String, String>();
			result.put("success", "false");
			result.put("msg", msg.toString());
			return result;
		}
		
		FileInputStream in = null;
		OutputStream os = null;
		try {
			in = new FileInputStream(new File(basePath + Constants.SubjectSbs.SUBJECT_SBS_DOC_TEMPLATE_PATH));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();  
			range.replaceText("${subjectName}", subject.getName());
			range.replaceText("${createUnitName}", subject.getCreateUnitName());
			range.replaceText("${creatorName}", subject.getCreatorName());
			range.replaceText("${address}", sbs.getAddress());
			range.replaceText("${phone}", sbs.getPhone());
			range.replaceText("${fax}", sbs.getFax());
			range.replaceText("${email}", sbs.getEmail());
			range.replaceText("${createTime}", sdf.format(subject.getCreateTime()));
			range.replaceText("${years}", sbs.getYears());
			range.replaceText("${xtyj}", sbs.getXtyj());
			range.replaceText("${yjmb}", sbs.getYjmb());
			range.replaceText("${jsgj}", sbs.getJsgj());
			range.replaceText("${yjff}", sbs.getYjff());
			range.replaceText("${syfa}", sbs.getSyfa());
			range.replaceText("${jdap}", sbs.getJdap());
			range.replaceText("${yqjg}", sbs.getYqjg());
			range.replaceText("${gztj}", sbs.getGztj());
			range.replaceText("${tjyj}", sbs.getTjyj());
			
			// 填充主要申请人信息
			for(int i = 0; i < 3; i ++) {
				if(i < primaryProposers.size()) {
					SubjectSbsProposerVo proposer = primaryProposers.get(i);
					range.replaceText("${name" + (i + 1) + "}", proposer.getName());
					range.replaceText("${xb" + (i + 1) + "}", proposer.getGender());
					range.replaceText("${sr" + (i + 1) + "}", proposer.getBirthday());
					range.replaceText("${zc" + (i + 1) + "}", proposer.getZw());
					range.replaceText("${xl" + (i + 1) + "}", proposer.getDegrees());
					range.replaceText("${xw" + (i + 1) + "}", "");
					range.replaceText("${byyx" + (i + 1) + "}", proposer.getUniversity());
					range.replaceText("${sxzy" + (i + 1) + "}", proposer.getMajor());
					range.replaceText("${dw" + (i + 1) + "}", proposer.getOrg());
					range.replaceText("${yjfg" + (i + 1) + "}", proposer.getWork());
					range.replaceText("${dh" + (i + 1) + "}", proposer.getPhone());
					range.replaceText("${dzxx" + (i + 1) + "}", proposer.getEmail());
					range.replaceText("${content" + (i + 1) + "}", proposer.getBackdrop());
				} else {
					range.replaceText("${name" + (i + 1) + "}", "");
					range.replaceText("${xb" + (i + 1) + "}", "");
					range.replaceText("${sr" + (i + 1) + "}", "");
					range.replaceText("${zc" + (i + 1) + "}", "");
					range.replaceText("${xl" + (i + 1) + "}", "");
					range.replaceText("${xw" + (i + 1) + "}", "");
					range.replaceText("${byyx" + (i + 1) + "}", "");
					range.replaceText("${sxzy" + (i + 1) + "}", "");
					range.replaceText("${dw" + (i + 1) + "}", "");
					range.replaceText("${yjfg" + (i + 1) + "}", "");
					range.replaceText("${dh" + (i + 1) + "}", "");
					range.replaceText("${dzxx" + (i + 1) + "}", "");
					range.replaceText("${content" + (i + 1) + "}", "");
				}
			}
			
			// 填充其它申请人信息
			List<SubjectSbsProposerVo> otherProposers = subjectSbsProposerService.getBySbsId(sbs.getSbsId(), Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_OTHER);
			for(int i = 0; i < 10; i ++) {
				int size = 0;
				if(otherProposers != null) {
					size = otherProposers.size();
				}
				if(i < size) {
					SubjectSbsProposerVo proposer = otherProposers.get(i);
					range.replaceText("${oname" + i + "}", proposer.getName());
					range.replaceText("${oage" + i + "}", String.valueOf(proposer.getAge()));
					range.replaceText("${oduty" + i + "}", proposer.getZw());
					range.replaceText("${oedu" + i + "}", proposer.getDegrees());
					range.replaceText("${odegree" + i + "}", "");
					range.replaceText("${omajor" + i + "}", proposer.getMajor());
					range.replaceText("${ounit" + i + "}", proposer.getOrg());
					range.replaceText("${owork" + i + "}", proposer.getWork());
				} else {
					range.replaceText("${oname" + i + "}", "");
					range.replaceText("${oage" + i + "}", "");
					range.replaceText("${oduty" + i + "}", "");
					range.replaceText("${oedu" + i + "}", "");
					range.replaceText("${odegree" + i + "}", "");
					range.replaceText("${omajor" + i + "}", "");
					range.replaceText("${ounit" + i + "}", "");
					range.replaceText("${owork" + i + "}", "");
				}
			}
			
			// 填充预算信息
			for(SubjectSbsBudget budget : budgets) {
				range.replaceText("${A_" + budget.getCode().substring(3) + "}", String.valueOf(budget.getCost()));
				range.replaceText("${D_" + budget.getCode().substring(3) + "}", budget.getReason() == null ? "" : budget.getReason());
			}
			
			os = new FileOutputStream(basePath + "/doc/sbs_" + subject.getId() + subject.getCreator() + ".doc");
			hdt.write(os);
			
			sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_SBADMIN_SP);
		} catch (Exception e) {
			e.printStackTrace();
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
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		if(subject.getType().equals(Constants.Subject.SUBJECT_TYPE_ZBKT)) {
			sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_KJS_SP);
			this.subjectSbsDao.update(sbs);
		} else if(subject.getType().equals(Constants.Subject.SUBJECT_TYPE_KYGGKT)) {
			sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_ORG_SP);
			this.subjectSbsDao.update(sbs);
		}
	}

	@Override
	public void sbadminUnpass(String subjectId, String comment) {
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_BACK);
		sbs.setComment(comment);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void orgadminPass(String subjectId) {
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_KJS_SP);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void orgadminUnpass(String subjectId, String comment) {
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_BACK);
		sbs.setComment(comment);
		this.subjectSbsDao.update(sbs);
	}

	@Override
	public void kjsadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_COMPLETE);
		this.subjectSbsDao.update(sbs);
		subject.setStage(Constants.Subject.SUBJECT_STAGE_RWSTB);
		this.subjectDao.save(subject);
	}

	@Override
	public void kjsadminUnpass(String subjectId, String comment) {
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_BACK);
		sbs.setComment(comment);
		this.subjectSbsDao.update(sbs);
	}

}
