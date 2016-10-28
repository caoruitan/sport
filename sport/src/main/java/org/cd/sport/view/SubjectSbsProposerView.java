package org.cd.sport.view;

import java.sql.Date;

/**
 * 用户vo对象
 * 
 * @author liuyk
 *
 */
public class SubjectSbsProposerView {
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
	 * 申报人毕业院校
	 */
	private String university;

	/**
	 * 申报人专业
	 */
	private String major;

	/**
	 * 申报人专业
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

	public String getSbsId() {
		return sbsId;
	}

	public void setSbsId(String sbsId) {
		this.sbsId = sbsId;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
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

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}

	public String getPrimary() {
		return primary;
	}

	public void setPrimary(String primary) {
		this.primary = primary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
