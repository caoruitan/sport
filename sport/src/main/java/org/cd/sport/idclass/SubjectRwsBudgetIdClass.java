package org.cd.sport.idclass;

import java.io.Serializable;

public class SubjectRwsBudgetIdClass implements Serializable {

	private static final long serialVersionUID = 4981072000260308255L;

	private String rwsId;

	private String code;

	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
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
		result = prime * result + ((rwsId == null) ? 0 : rwsId.hashCode());
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
		SubjectRwsBudgetIdClass other = (SubjectRwsBudgetIdClass) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (rwsId == null) {
			if (other.rwsId != null)
				return false;
		} else if (!rwsId.equals(other.rwsId))
			return false;
		return true;
	}

}
