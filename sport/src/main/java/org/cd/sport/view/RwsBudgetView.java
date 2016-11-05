package org.cd.sport.view;

import java.util.List;

/**
 * 
 * 申报预算
 * 
 * @author liuyk
 *
 */
public class RwsBudgetView {
	private String rwsId;
	private List<Budget> income;
	private List<Budget> cost;

	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
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
