package org.cd.sport.domain;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 用户实体
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_USER")
public class UserDomain {
	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户名/登录名(必填)
	 */
	private String loginName;
	/**
	 * 用户登录密码(必填)
	 */
	private String password;
	/**
	 * 用户真实姓名(必填)
	 */
	private String userName;
	/**
	 * 用户性别0:男性,1:女性(必填)
	 */
	private int gender;
	/**
	 * 证件类型(必填)
	 */
	private String credType;

	/**
	 * 证件编号(必填)
	 */
	private String credNo;
	/**
	 * 用户角色(必填)
	 */
	private String role;
	/**
	 * 用户所属单位/公司
	 */
	private String organization;

	/**
	 * 用户生日(非必填)
	 */
	private Date birthday;

	/**
	 * 用户邮箱(非必填)
	 */
	private String email;

	/**
	 * 用户职称(非必填)
	 */
	private String zc;
	/**
	 * 用户职务(非必填)
	 */
	private String zw;
	/**
	 * 用户部门(非必填)
	 */
	private String dept;

	/**
	 * 用户学历(非必填)
	 */
	private String degrees;

	/**
	 * 用户专业(非必填)
	 */
	private String major;
	/**
	 * 用户座机(非必填)
	 */
	private String telephone;

	/**
	 * 用户手机(非必填)
	 */
	private String phone;

	/**
	 * 用户地址(非必填)
	 */
	private String address;

	@Id
	@Column(name = "USER_ID")
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "LOGIN_NAME")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "GENDER")
	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name = "CRED_TYPE")
	public String getCredType() {
		return credType;
	}

	public void setCredType(String credType) {
		this.credType = credType;
	}

	@Column(name = "CRED_NO")
	public String getCredNo() {
		return credNo;
	}

	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}

	@Column(name = "ROLE")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "ORGANIZATION")
	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ZC")
	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	@Column(name = "ZW")
	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	@Column(name = "DEPT")
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}