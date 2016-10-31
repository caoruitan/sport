package org.cd.sport.view;

import java.util.List;

/**
 * 
 * 申报预算
 * 
 * @author liuyk
 *
 */
public class SbsBudgetView {
	private String sbsId;
	private List<Budget> income;
	private List<Budget> cost;

	public String getSbsId() {
		return sbsId;
	}

	public void setSbsId(String sbsId) {
		this.sbsId = sbsId;
	}

	public List<Budget> getIncome() {
		return income;
	}

	public void setIncome(List<Budget> income) {
		this.income = income;
	}

	public List<Budget> getCost() {
		return cost;
	}

	public void setCost(List<Budget> cost) {
		this.cost = cost;
	}
}
