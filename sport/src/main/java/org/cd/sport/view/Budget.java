package org.cd.sport.view;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.utils.Nullable;

public class Budget implements Nullable {
	private String cost;
	private String code;
	private String name;
	private String reason;

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public boolean isNull() {
		return StringUtils.isBlank(cost) && StringUtils.isBlank(name) && StringUtils.isBlank(code)
				&& StringUtils.isBlank(reason);
	}

}
