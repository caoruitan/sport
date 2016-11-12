package org.cd.sport.idclass;

import java.io.Serializable;
/**
 * 
 * 结题主键
 * @author liuyk
 *
 */
public class ConclusionAttachmentIdClass implements Serializable {

	private static final long serialVersionUID = 4662333585956898674L;

	private String fileId;

	private String conclusionId;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getConclusionId() {
		return conclusionId;
	}

	public void setConclusionId(String conclusionId) {
		this.conclusionId = conclusionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conclusionId == null) ? 0 : conclusionId.hashCode());
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConclusionAttachmentIdClass other = (ConclusionAttachmentIdClass) obj;
		if (conclusionId == null) {
			if (other.conclusionId != null)
				return false;
		} else if (!conclusionId.equals(other.conclusionId))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		return true;
	}

}
