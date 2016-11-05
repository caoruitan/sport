package org.cd.sport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SPORT_SUBJECT_RWS_UNDERTAKER")
public class SubjectRwsUndertaker {
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
	private int age;

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

	@Column(name = "RWS_ID")
	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
	}

	@Column(name = "SUBJECT_ID")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ZW")
	public String getZw() {
		return zw;
	}

	@Column(name = "AGE")
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	@Column(name = "DEGREES")
	public String getDegrees() {
		return degrees;
	}

	public void setDegrees(String degrees) {
		this.degrees = degrees;
	}

	@Column(name = "MAJOR")
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

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
