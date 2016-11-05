package org.cd.sport.vo;

import javax.persistence.Column;

public class SubjectRwsUndertakerVo {
	/**
	 * 任务书ID
	 */
	private String rwsId;
	/**
	 * 课题ID
	 */
	private String subjectId;

	/**
	 * 承担人ID
	 */
	private String id;
	/**
	 * 承担人姓名
	 */
	private String name;
	/**
	 * 承担人生日
	 */
	private String age;

	/**
	 * 承担人职务
	 */
	private String zw;

	/**
	 * 承担人学历
	 */
	private String degrees;

	/**
	 * 承担人专业
	 */
	private String major;

	/**
	 * 承担人单位
	 */
	private String org;

	/**
	 * 承担人研究分工
	 */
	private String work;

	/**
	 * 承担人类型
	 */
	private String type;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "ORG")
	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	@Column(name = "WORK")
	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
