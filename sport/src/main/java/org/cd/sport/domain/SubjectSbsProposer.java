package org.cd.sport.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SPORT_SUBJECT_SBS_PROPOSER")
public class SubjectSbsProposer {
	/**
	 * 申报书ID
	 */
	private String sbsId;
	/**
	 * 课题ID
	 */
	private String subjectId;

	/**
	 * 申报人ID
	 */
	private String id;
	/**
	 * 申报人姓名
	 */
	private String name;
	/**
	 * 申报人性别
	 */
	private int gender;
	/**
	 * 申报人生日
	 */
	private Date birthday;

	/**
	 * 申报人职务
	 */
	private String zw;

	/**
	 * 申报人学历
	 */
	private String degrees;

	/**
	 * 申请人学位
	 */
	private String xuewei;

	/**
	 * 申报人毕业院校
	 */
	private String university;

	/**
	 * 申报人专业
	 */
	private String major;

	/**
	 * 申报人单位
	 */
	private String org;

	/**
	 * 申报人研究分工
	 */
	private String work;

	/**
	 * 申报人电话
	 */
	private String phone;

	/**
	 * 申报人邮件
	 */
	private String email;

	/**
	 * 申报人背景
	 */
	private String backdrop;

	/**
	 * 是否为主要申请人
	 */
	private String primary;

	/**
	 * 排序
	 */
	private int sort;

	@Column(name = "SBS_ID")
	public String getSbsId() {
		return sbsId;
	}

	public void setSbsId(String sbsId) {
		this.sbsId = sbsId;
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

	@Column(name = "GENDER")
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "ZW")
	public String getZw() {
		return zw;
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

	@Column(name = "UNIVERSITY")
	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
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

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "BACKDROP")
	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}

	@Column(name = "IS_PRIMARY")
	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	@Column(name = "SORT")
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Column(name = "XUE_WEI")
	public String getXuewei() {
		return xuewei;
	}

	public void setXuewei(String xuewei) {
		this.xuewei = xuewei;
	}
}
