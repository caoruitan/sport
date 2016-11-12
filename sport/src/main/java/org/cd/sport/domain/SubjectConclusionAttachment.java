package org.cd.sport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.cd.sport.idclass.ConclusionAttachmentIdClass;

/**
 * 结题报告
 * 
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_SUBJECT_CONCLUSION_ATTACHMENT")
@IdClass(ConclusionAttachmentIdClass.class)
public class SubjectConclusionAttachment {

	private String subjectId;
	private String conclusionId;
	private String fileId;
	private String fileName;
	private String path;

	@Column(name = "SUBJECT_ID")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Id
	@Column(name = "CONCLUSION_ID")
	public String getConclusionId() {
		return conclusionId;
	}

	public void setConclusionId(String conclusionId) {
		this.conclusionId = conclusionId;
	}

	@Id
	@Column(name = "FILE_ID")
	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Column(name = "FILE_PATH")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
