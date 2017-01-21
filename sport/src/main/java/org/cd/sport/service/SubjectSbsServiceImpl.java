package org.cd.sport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.util.IOUtils;
import org.cd.sport.action.UploadAction;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectDao;
import org.cd.sport.dao.SubjectSbsDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectSbs;
import org.cd.sport.domain.SubjectSbsBudget;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.filter.SportListener;
import org.cd.sport.utils.AuthenticationUtils;
import org.cd.sport.utils.DocUtils;
import org.cd.sport.vo.SubjectSbsProposerVo;
import org.cd.sport.vo.UserVo;
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
		return this.getOrCreateSubjectSbs(subjectId);
	}

	@Override
	public SubjectSbs createSubjectSbs(String subjectId) {
		SubjectSbs sbs = new SubjectSbs();
		sbs.setSubjectId(subjectId);
		sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_SBOPER_TB);
		this.subjectSbsDao.save(sbs);
		this.subjectSbsProposerService.createBySubjectId(subjectId);
		this.copySbsFile(subjectId, SportListener.getBasePath());
		return sbs;
	}

	public void copySbsFile(String sujectId, String basePath) {
		UserVo user = AuthenticationUtils.getUser();
		if (user == null) {
			throw new ParameterIsWrongException();
		}
		String dir = basePath + "/" + UploadAction.DOC_DIR;
		Subject subject = this.subjectService.getSubjectById(sujectId);
		SubjectSbs sbs = this.getSbsBySubjectId(sujectId);
		// 源文件前缀
		String src_pre = dir + "/sbs_new_template_";
		// 目标文件名前缀
		String desc_pre = dir + "/sbs_new_" + sujectId + user.getLoginName() + "_";
		// 文件后缀
		String end = ".doc";
		FileInputStream is = null;
		OutputStream out = null;
		try {
			// // 拷贝封面
			// InputStream is = new FileInputStream(new File(src_pre + "000" +
			// end));
			// HWPFDocument hdt = new HWPFDocument(is);
			// Range range = hdt.getRange();
			// 处理默认值
			// range.replaceText("${subjectName}", subject.getName());
			// range.replaceText("${createUnitName}",
			// subject.getCreateUnitName());
			// range.replaceText("${creatorName}", subject.getCreatorName());
			// OutputStream out = new FileOutputStream(new File(desc_pre + "000"
			// + end));
			// hdt.write(out);

			// 拷贝01文件
			is = new FileInputStream(new File(src_pre + "001" + end));
			out = new FileOutputStream(new File(desc_pre + "001" + end));
			IOUtils.copy(is, out);
			// is = new FileInputStream(new File(src_pre + "009" + end));
			// out = new FileOutputStream(new File(desc_pre + "009" + end));
			// IOUtils.copy(is, out);

			// is = new FileInputStream(new File(src_pre + "010" + end));
			// out = new FileOutputStream(new File(desc_pre + "010" + end));
			// IOUtils.copy(is, out);
			// 拷贝预算原因文件
			is = new FileInputStream(new File(src_pre + "011_r" + end));
			out = new FileOutputStream(new File(desc_pre + "011" + end));
			IOUtils.copy(is, out);

			is = new FileInputStream(new File(src_pre + "012" + end));
			HWPFDocument hdt = new HWPFDocument(is);
			Range range = hdt.getRange();
			range.replaceText("${createUnitName}", subject.getCreateUnitName());
			range.replaceText("${address}", StringUtils.isBlank(sbs.getAddress()) ? "" : sbs.getAddress());
			range.replaceText("${phone}", StringUtils.isBlank(sbs.getPhone()) ? "" : sbs.getPhone());
			range.replaceText("${fax}", StringUtils.isBlank(sbs.getFax()) ? "" : sbs.getFax());
			out = new FileOutputStream(new File(desc_pre + "012" + end));
			hdt.write(out);

		} catch (Exception e) {

		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private SubjectSbs getOrCreateSubjectSbs(String subjectId) {
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		if (sbs == null) {
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
		basePath = SportListener.getBasePath();
		SubjectSbs sbs = this.getSbsBySubjectId(subjectId);
		if (sbs == null) {
			Map<String, String> result = new HashMap<String, String>();
			result.put("success", "false");
			result.put("msg", "申报书尚未填报");
			return result;
		}

		boolean complete = true;
		StringBuilder msg = new StringBuilder("");
		// 验证基本信息是否完整
		if (sbs.getAddress() == null || sbs.getAddress().equals("")) {
			msg.append("通讯地址未填写<br/>");
			complete = false;
		}
		if (sbs.getPhone() == null || sbs.getPhone().equals("")) {
			msg.append("联系电话未填写<br/>");
			complete = false;
		}
		if (sbs.getFax() == null || sbs.getFax().equals("")) {
			msg.append("传真未填写<br/>");
			complete = false;
		}
		if (sbs.getEmail() == null || sbs.getEmail().equals("")) {
			msg.append("电子信箱未填写<br/>");
			complete = false;
		}
		if (sbs.getYears() == null || sbs.getYears().equals("")) {
			msg.append("完成年限未填写<br/>");
			complete = false;
		}
		// if (sbs.getXtyj() == null || sbs.getXtyj().equals("")) {
		// msg.append("选题依据未填写<br/>");
		// complete = false;
		// }
		// if (sbs.getYjmb() == null || sbs.getYjmb().equals("")) {
		// msg.append("研究目标和主要研究内容未填写<br/>");
		// complete = false;
		// }
		// if (sbs.getJsgj() == null || sbs.getJsgj().equals("")) {
		// msg.append("技术关键和创新点未填写<br/>");
		// complete = false;
		// }
		// if (sbs.getYjff() == null || sbs.getYjff().equals("")) {
		// msg.append("拟采取的研究方法、主要技术路线、主要指标及可行性分析未填写<br/>");
		// complete = false;
		// }
		// if (sbs.getSyfa() == null || sbs.getSyfa().equals("")) {
		// msg.append("研究实验方案、实验地点及联合申请单位的分工未填写<br/>");
		// complete = false;
		// }
		// if (sbs.getJdap() == null || sbs.getJdap().equals("")) {
		// msg.append("进度安排未填写<br/>");
		// complete = false;
		// }
		// if (sbs.getYqjg() == null || sbs.getYqjg().equals("")) {
		// msg.append("预期结果未填写<br/>");
		// complete = false;
		// }
		// if (sbs.getGztj() == null || sbs.getGztj().equals("")) {
		// msg.append("申报单位现有工作条件和基础未填写<br/>");
		// complete = false;
		// }
		List<SubjectSbsProposerVo> primaryProposers = subjectSbsProposerService.getBySbsId(sbs.getSbsId(),
				Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_PRIMARY);
		if (primaryProposers == null || primaryProposers.isEmpty()) {
			msg.append("申请人员情况未填写<br/>");
			complete = false;
		}
		List<SubjectSbsBudget> budgets = subjectSbsBudgetService.getBySbsId(sbs.getSbsId());
		if (budgets == null || budgets.isEmpty()) {
			msg.append("经费预算未填写<br/>");
			complete = false;
		}
		// if (sbs.getTjyj() == null || sbs.getTjyj().equals("")) {
		// msg.append("申报单位推荐意见及提供相关研究工作条件的保证未填写<br/>");
		// complete = false;
		// }

		if (complete == false) {
			Map<String, String> result = new HashMap<String, String>();
			result.put("success", "false");
			result.put("msg", msg.toString());
			return result;
		}

		try {
			// 如果课题为2、说明为新的
			String sbs_prefix = "/" + UploadAction.DOC_DIR + (subject.getNewState() == 2 ? "/sbs_new_" : "/sbs_");
			// 封面处理
			this.createFm(basePath, sbs_prefix, subject, sbs);
			// 文档性内容 为单个文件 不处理 TODO
			// 填充申请人
			this.createProposers(basePath, sbs_prefix, subject, sbs, primaryProposers);
			// 填充预算信息
			this.createBudget(basePath, sbs_prefix, subject, sbs, budgets);
			// 填充预算理由信息
			this.createBudgetReason(basePath, sbs_prefix, subject, sbs, budgets);
			// 本单位意见
			// this.createOrg(basePath, sbs_prefix, subject, sbs, budgets,
			// loginName);
			// 合并文件
			this.megreFile(basePath, sbs_prefix, subject);
			sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_SBADMIN_SP);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, String> result = new HashMap<String, String>();
		result.put("success", "true");
		return result;
	}

	@Override
	public void sbadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectSbs sbs = this.subjectSbsDao.getSbsBySubjectId(subjectId);
		if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_ZBKT)) {
			sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_KJS_SP);
			this.subjectSbsDao.update(sbs);
		} else if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_KYGGKT)) {
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

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Date curDate = new Date();
		String year = sdf.format(curDate);
		if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_ZBKT)) {
			String maxNum = this.subjectDao.getMaxSubjectNum(year, Constants.Subject.SUBJECT_TYPE_ZBKT);
			String num = "";
			if (maxNum.equals("")) {
				num = year + Constants.Subject.SUBJECT_TYPE_ZBKT_NUM_PREFIX + "001";
			} else {
				String count = maxNum.substring(5);
				int curCount = Integer.parseInt(count) + 1;
				if (curCount <= 9) {
					num = year + Constants.Subject.SUBJECT_TYPE_ZBKT_NUM_PREFIX + "00" + String.valueOf(curCount);
				} else if (curCount > 9) {
					num = year + Constants.Subject.SUBJECT_TYPE_ZBKT_NUM_PREFIX + "0" + String.valueOf(curCount);
				} else if (curCount > 99) {
					num = year + Constants.Subject.SUBJECT_TYPE_ZBKT_NUM_PREFIX + String.valueOf(curCount);
				}
			}
			subject.setNum(num);
		} else if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_KYGGKT)) {
			String maxNum = this.subjectDao.getMaxSubjectNum(year, Constants.Subject.SUBJECT_TYPE_KYGGKT);
			String num = "";
			if (maxNum.equals("")) {
				num = year + Constants.Subject.SUBJECT_TYPE_KYGGKT_NUM_PREFIX + "001";
			} else {
				String count = maxNum.substring(5);
				int curCount = Integer.parseInt(count) + 1;
				if (curCount <= 9) {
					num = year + Constants.Subject.SUBJECT_TYPE_KYGGKT_NUM_PREFIX + "00" + String.valueOf(curCount);
				} else if (curCount > 9) {
					num = year + Constants.Subject.SUBJECT_TYPE_KYGGKT_NUM_PREFIX + "0" + String.valueOf(curCount);
				} else if (curCount > 99) {
					num = year + Constants.Subject.SUBJECT_TYPE_KYGGKT_NUM_PREFIX + String.valueOf(curCount);
				}
			}
			subject.setNum(num);
		}

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

	/**
	 * 创建封面文件
	 */
	public void createFm(String basePath, String sbs_prefix, Subject subject, SubjectSbs sbs) throws IOException {
		File fmFile = new File(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_000.doc");
		if (fmFile.exists()) {
			fmFile.delete();
		}
		FileInputStream in = null;
		OutputStream os = null;
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			in = new FileInputStream(new File(basePath + Constants.SubjectSbs.SUBJECT_SBS_FM_TEMPLATE_PATH));
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
			os = new FileOutputStream(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_000.doc");
			hdt.write(os);
		} finally {
			if (in != null) {
				in.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 创建申请人文件
	 */
	public void createProposers(String basePath, String sbs_prefix, Subject subject, SubjectSbs sbs,
			List<SubjectSbsProposerVo> primaryProposers) throws IOException {
		File pFile = new File(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_009.doc");
		if (pFile.exists()) {
			pFile.delete();
		}
		FileInputStream in = null;
		OutputStream os = null;
		try {
			in = new FileInputStream(new File(basePath + Constants.SubjectSbs.SUBJECT_SBS_PROPOSERS_TEMPLATE_PATH));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();
			// 填充主要申请人信息
			for (int i = 0; i < 3; i++) {
				if (i < primaryProposers.size()) {
					SubjectSbsProposerVo proposer = primaryProposers.get(i);
					range.replaceText("${name" + (i + 1) + "}", proposer.getName());
					range.replaceText("${xb" + (i + 1) + "}", proposer.getGender());
					range.replaceText("${sr" + (i + 1) + "}",
							proposer.getBirthday() == null ? "" : proposer.getBirthday());
					range.replaceText("${zc" + (i + 1) + "}", proposer.getZw());
					range.replaceText("${xl" + (i + 1) + "}", proposer.getDegrees());
					range.replaceText("${xw" + (i + 1) + "}", "");
					range.replaceText("${byyx" + (i + 1) + "}",
							proposer.getUniversity() == null ? "" : proposer.getUniversity());
					range.replaceText("${sxzy" + (i + 1) + "}", proposer.getMajor() == null ? "" : proposer.getMajor());
					range.replaceText("${dw" + (i + 1) + "}", proposer.getOrg() == null ? "" : proposer.getOrg());
					range.replaceText("${yjfg" + (i + 1) + "}", proposer.getWork() == null ? "" : proposer.getWork());
					range.replaceText("${dh" + (i + 1) + "}", proposer.getPhone() == null ? "" : proposer.getPhone());
					range.replaceText("${dzxx" + (i + 1) + "}", proposer.getEmail() == null ? "" : proposer.getEmail());
					range.replaceText("${content" + (i + 1) + "}",
							proposer.getBackdrop() == null ? "" : proposer.getBackdrop());
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
			List<SubjectSbsProposerVo> otherProposers = subjectSbsProposerService.getBySbsId(sbs.getSbsId(),
					Constants.SubjectSbs.SUBJECT_SBS_PROPOSER_OTHER);
			for (int i = 0; i < 10; i++) {
				int size = 0;
				if (otherProposers != null) {
					size = otherProposers.size();
				}
				if (i < size) {
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

			os = new FileOutputStream(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_009.doc");
			hdt.write(os);

		} finally {
			if (in != null) {
				in.close();
			}
			if (os != null) {
				os.close();
			}

		}
	}

	/**
	 * 创建预算
	 */
	public void createBudget(String basePath, String sbs_prefix, Subject subject, SubjectSbs sbs,
			List<SubjectSbsBudget> budgets) throws IOException {
		File pFile = new File(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_010.doc");
		if (pFile.exists()) {
			pFile.delete();
		}
		FileInputStream in = null;
		OutputStream os = null;
		try {

			in = new FileInputStream(new File(basePath + Constants.SubjectSbs.SUBJECT_SBS_BUDGET_TEMPLATE_PATH));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();
			// 填充预算信息
			for (SubjectSbsBudget budget : budgets) {
				range.replaceText("${A_" + budget.getCode().substring(3) + "}", String.valueOf(budget.getCost()));
			}
			os = new FileOutputStream(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_010.doc");
			hdt.write(os);
		} finally {
			if (in != null) {
				in.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 创建预算
	 */
	public void createBudgetReason(String basePath, String sbs_prefix, Subject subject, SubjectSbs sbs,
			List<SubjectSbsBudget> budgets) throws IOException {
		File pFile = new File(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_011.doc");
		if (pFile.exists()) {
			pFile.delete();
		}
		FileInputStream in = null;
		OutputStream os = null;
		try {

			in = new FileInputStream(new File(basePath + Constants.SubjectSbs.SUBJECT_SBS_BUDGET_REASON_TEMPLATE_PATH));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();
			// 填充预算信息
			for (SubjectSbsBudget budget : budgets) {
				range.replaceText("${D_" + budget.getCode().substring(3) + "}",
						budget.getReason() == null ? "" : budget.getReason());
			}
			os = new FileOutputStream(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_011.doc");
			hdt.write(os);
		} finally {
			if (in != null) {
				in.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 创建预算
	 */
	public void createOrg(String basePath, String sbs_prefix, Subject subject, SubjectSbs sbs,
			List<SubjectSbsBudget> budgets) throws IOException {
		File pFile = new File(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_012.doc");
		if (pFile.exists()) {
			pFile.delete();
		}
		FileInputStream in = null;
		OutputStream os = null;
		try {

			in = new FileInputStream(new File(basePath + Constants.SubjectSbs.SUBJECT_SBS_ORG_TEMPLATE_PATH));
			HWPFDocument hdt = new HWPFDocument(in);
			Range range = hdt.getRange();
			range.replaceText("${createUnitName}", subject.getOrganizationName());
			range.replaceText("${address}", sbs.getAddress());
			range.replaceText("${phone}", sbs.getPhone());
			range.replaceText("${fax}", sbs.getFax());
			os = new FileOutputStream(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_012.doc");
			hdt.write(os);
		} finally {
			if (in != null) {
				in.close();
			}
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 合并文件
	 */
	public void megreFile(String basePath, String sbs_prefix, Subject subject) {
		List<String> files = new ArrayList<String>();
		files.add(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_001.doc");
		files.add(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_000.doc");
		files.add(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_009.doc");
		files.add(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_010.doc");
		files.add(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_011.doc");
		files.add(basePath + sbs_prefix + subject.getId() + subject.getCreator() + "_012.doc");
		files.add(basePath + "/" + UploadAction.DOC_DIR + "/sbs_new_template_013.doc");
		files.add(basePath + "/" + UploadAction.DOC_DIR + "/sbs_new_template_014.doc");
		files.add(basePath + "/" + UploadAction.DOC_DIR + "/sbs_new_template_015.doc");
		DocUtils.uniteDoc(files,
				basePath + "/" + UploadAction.DOC_DIR + "/sbs_" + subject.getId() + subject.getCreator() + ".doc");
	}
}
