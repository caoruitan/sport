package org.cd.sport.idclass;

import java.io.Serializable;

public class SubjectSbsBudgetIdClass implements Serializable {

	private static final long serialVersionUID = 4981072000260308255L;

	private String sbsId;

	private String code;

	public String getSbsId() {
		return sbsId;
	}

	public void setSbsId(String sbsId) {
		this.sbsId = sbsId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((sbsId == null) ? 0 : sbsId.hashCode());
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
		SubjectSbsBudgetIdClass other = (SubjectSbsBudgetIdClass) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (sbsId == null) {
			if (other.sbsId != null)
				return false;
		} else if (!sbsId.equals(other.sbsId))
			return false;
		return true;
	}
}
