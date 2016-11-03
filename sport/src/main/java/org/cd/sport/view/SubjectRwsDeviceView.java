package org.cd.sport.view;

import javax.persistence.Column;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
public class SubjectRwsDeviceView {

	/**
	 * 课题id
	 */
	private String subjectId;
	/**
	 * 任务书id
	 */
	private String rwsId;

	/**
	 * 主键
	 */
	private String dId;

	/**
	 * 设备名称
	 */
	private String name;

	/**
	 * 用途
	 */
	private String purpose;

	/**
	 * 指标
	 * 
	 */
	private String norm;

	/**
	 * 购置或者试制
	 */
	private String buy;

	/**
	 * 单价
	 */
	private String price;

	/**
	 * 购买数量
	 */
	private String num;

	/**
	 * 申请从专项经费中列支数
	 */
	private String slzs;

	/**
	 * 购置地区
	 */
	private String orgin;

	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
	}

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	@Column(name = "NORM")
	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}

	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getSlzs() {
		return slzs;
	}

	public void setSlzs(String slzs) {
		this.slzs = slzs;
	}

	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}
}
