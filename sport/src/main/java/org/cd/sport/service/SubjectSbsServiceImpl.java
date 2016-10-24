package org.cd.sport.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectSbsDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectSbs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectSbsServiceImpl implements SubjectSbsService {
	
	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectSbsDao subjectSbsDao;

	@Override
	public SubjectSbs getSubjectSbsById(String id) {
		return this.subjectSbsDao.getEntityById(SubjectSbs.class, id);
	}
	
	@Override
	public SubjectSbs getSbsBySubjectId(String subjectId) {
		return this.subjectSbsDao.getSbsBySubjectId(subjectId);
	}

	@Override
	public SubjectSbs createSubjectSbs(String subjectId) {
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
	public void checkAndSubmit(String subjectId, String basePath) {
		Subject subject = subjectService.getSubjectById(subjectId);
		SubjectSbs sbs = this.getSbsBySubjectId(subjectId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
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
			os = new FileOutputStream(basePath + "/WEB-INF/doc/sbs_" + subject.getId() + subject.getCreator() + ".doc");
			hdt.write(os);
			
			sbs.setStatus(Constants.SubjectSbs.SUBJECT_SBS_STATUS_SBADMIN_SP);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			in.close();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
