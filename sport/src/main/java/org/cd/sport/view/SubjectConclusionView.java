package org.cd.sport.view;

import java.util.List;

public class SubjectConclusionView {
	private String subjectId;
	private String conclusionId;
	private List<FileView> files;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getConclusionId() {
		return conclusionId;
	}

	public void setConclusionId(String conclusionId) {
		this.conclusionId = conclusionId;
	}

	public List<FileView> getFiles() {
		return files;
	}

	public void setFiles(List<FileView> files) {
		this.files = files;
	}

}
