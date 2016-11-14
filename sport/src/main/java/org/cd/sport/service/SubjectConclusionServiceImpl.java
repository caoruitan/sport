package org.cd.sport.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.dao.SubjectConclusionDao;
import org.cd.sport.dao.SubjectDao;
import org.cd.sport.domain.Subject;
import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectConclusionAttachment;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.utils.NullableUtils;
import org.cd.sport.view.FileView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 结题报告业务
 * 
 * @author liuyk
 *
 */
@Service
@Transactional(readOnly = true)
public class SubjectConclusionServiceImpl implements SubjectConclusionService {

	@Autowired
	private SubjectConclusionDao subjectConclusionDao;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SubjectDao subjectDao;

	@Override
	@Transactional
	public SubjectConclusion createSubjectConclusion(String subjectId) {
		SubjectConclusion sc = this.subjectConclusionDao.findSubjectConclusionBySubjectId(subjectId);
		if (sc != null) {
			return sc;
		}
		sc = new SubjectConclusion();
		sc.setSubjectId(subjectId);
		sc.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_SBOPER_TB);
		this.subjectConclusionDao.save(sc);
		return sc;
	}

	@Override
	@Transactional
	public boolean createConclusionAttachment(List<SubjectConclusionAttachment> cas) {
		if (cas == null || cas.isEmpty()) {
			return false;
		}
		for (SubjectConclusionAttachment cs : cas) {
			this.subjectConclusionDao.save(cs);
		}
		return true;
	}

	@Override
	@Transactional
	public boolean createConclusionAttachment(String conclusionId, List<FileView> files) {
		NullableUtils.clean(files);
		if (StringUtils.isBlank(conclusionId) || files == null || files.isEmpty()) {
			return false;
		}
		SubjectConclusion sc = getSubjectConclusionById(conclusionId);
		if (sc == null) {
			throw new EntityNotFoundException("结题报告不存在");
		}
		this.subjectConclusionDao.deleteAttachmentByConclusionId(conclusionId);
		List<SubjectConclusionAttachment> cas = new ArrayList<SubjectConclusionAttachment>();
		for (FileView fileView : files) {
			SubjectConclusionAttachment sa = new SubjectConclusionAttachment();
			sa.setConclusionId(conclusionId);
			sa.setFileId(fileView.getId());
			sa.setFileName(fileView.getName());
			sa.setPath(fileView.getPath());
			sa.setSubjectId(sc.getSubjectId());
			cas.add(sa);
		}
		return this.createConclusionAttachment(cas);
	}

	@Override
	@Transactional
	public boolean deleteAttachmentByConclusionId(String conclusionId) {
		return this.subjectConclusionDao.deleteAttachmentByConclusionId(conclusionId);
	}

	@Override
	@Transactional
	public boolean deleteAttachmentByConclusionId(String conclusionId, String fileId) {
		return this.subjectConclusionDao.deleteAttachmentByConclusionId(conclusionId, fileId);
	}

	@Override
	@Transactional
	public SubjectConclusion getSubjectConclusionBySubjectId(String subjectId) {
		return this.createSubjectConclusion(subjectId);
	}

	@Override
	public SubjectConclusion getSubjectConclusionById(String conclusionId) {
		if (StringUtils.isBlank(conclusionId)) {
			return null;
		}
		return this.subjectConclusionDao.getEntityById(SubjectConclusion.class, conclusionId);
	}

	@Override
	public List<SubjectConclusionAttachment> getAttachmentByConclusionId(String conclusionId) {
		return this.subjectConclusionDao.findAttachmentByConclusionId(conclusionId);
	}

	@Override
	public List<SubjectConclusionAttachment> getAttachmentBySubjectId(String subjectId) {
		return this.subjectConclusionDao.findAttachmentBySubjectId(subjectId);
	}

	@Override
	@Transactional
	public Map<String, String> checkAndSubmit(String subjectId, String basePath) {
		Subject subject = subjectService.getSubjectById(subjectId);
		if (subject == null) {
			throw new ParameterIsWrongException("课题不存在");
		}

		if (!Constants.Subject.SUBJECT_STAGE_JTBG.equals(subject.getStage())) {
			throw new ParameterIsWrongException("课题状态不正确");
		}

		SubjectConclusion subjectCon = this.getSubjectConclusionBySubjectId(subjectId);
		Map<String, String> result = new HashMap<String, String>();
		if (subjectCon == null) {
			result.put("success", "false");
			result.put("msg", "结题报告尚未填报");
			return result;
		}

		List<SubjectConclusionAttachment> sas = this.getAttachmentByConclusionId(subjectCon.getConclusionId());
		if (sas == null || sas.isEmpty()) {
			result.put("success", "false");
			result.put("msg", "结题报告附件未上传<br/>");
			return result;
		}

		subjectCon.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_SBADMIN_SP);
		return result;
	}

	@Override
	public void sbadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectConclusion rws = this.getSubjectConclusionBySubjectId(subjectId);
		if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_ZBKT)) {
			rws.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_KJS_SP);
			this.subjectConclusionDao.update(rws);
		} else if (subject.getType().equals(Constants.Subject.SUBJECT_TYPE_KYGGKT)) {
			rws.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_ORG_SP);
			this.subjectConclusionDao.update(rws);
		}
	}

	@Override
	public void sbadminUnpass(String subjectId, String comment) {
		SubjectConclusion rws = this.getSubjectConclusionBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_BACK);
		rws.setComment(comment);
		this.subjectConclusionDao.update(rws);
	}

	@Override
	public void orgadminPass(String subjectId) {
		SubjectConclusion rws = this.getSubjectConclusionBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_KJS_SP);
		this.subjectConclusionDao.update(rws);
	}

	@Override
	public void orgadminUnpass(String subjectId, String comment) {
		SubjectConclusion rws = this.getSubjectConclusionBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_BACK);
		rws.setComment(comment);
		this.subjectConclusionDao.update(rws);
	}

	@Override
	public void kjsadminPass(String subjectId) {
		Subject subject = this.subjectService.getSubjectById(subjectId);
		SubjectConclusion rws = this.getSubjectConclusionBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_COMPLETE);
		this.subjectConclusionDao.update(rws);
		subject.setStage(Constants.Subject.SUBJECT_STAGE_JTBG);
		this.subjectDao.save(subject);
	}

	@Override
	public void kjsadminUnpass(String subjectId, String comment) {
		SubjectConclusion rws = this.getSubjectConclusionBySubjectId(subjectId);
		rws.setStatus(Constants.SubjectConclusion.SUBJECT_CONCLUSION_STATUS_BACK);
		rws.setComment(comment);
		this.subjectConclusionDao.update(rws);
	}
}
