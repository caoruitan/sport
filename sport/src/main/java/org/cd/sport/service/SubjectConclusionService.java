package org.cd.sport.service;

import java.util.List;
import java.util.Map;

import org.cd.sport.domain.SubjectConclusion;
import org.cd.sport.domain.SubjectConclusionAttachment;
import org.cd.sport.view.FileView;

/**
 * 结题报告业务层
 * 
 * @author liuyk
 *
 */
public interface SubjectConclusionService {

	public SubjectConclusion createSubjectConclusion(String subjectId);

	public boolean createConclusionAttachment(List<SubjectConclusionAttachment> cas);

	public boolean createConclusionAttachment(String conclusionId, List<FileView> files);

	public boolean deleteAttachmentByConclusionId(String conclusionId);

	public boolean deleteAttachmentByConclusionId(String conclusionId, String fileId);

	public void sbadminPass(String subjectId);

	public void sbadminUnpass(String subjectId, String comment);

	public void orgadminPass(String subjectId);

	public void orgadminUnpass(String subjectId, String comment);

	public void kjsadminPass(String subjectId);

	public void kjsadminUnpass(String subjectId, String comment);

	public Map<String, String> checkAndSubmit(String subjectId, String basePath);

	public SubjectConclusion getSubjectConclusionBySubjectId(String subjectId);

	public SubjectConclusion getSubjectConclusionById(String conclusionId);

	public List<SubjectConclusionAttachment> getAttachmentByConclusionId(String conclusionId);

	public List<SubjectConclusionAttachment> getAttachmentBySubjectId(String subjectId);

}
