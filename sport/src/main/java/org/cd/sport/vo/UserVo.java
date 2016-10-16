package org.cd.sport.vo;

import java.util.Date;

/**
 * 用户vo对象
 * 
 * @author liuyk
 *
 */
public class UserVo {
	/**
	 * 用户id
	 */
	private String userId;

	/**
	 * 用户名/登录名(必填)
	 */
	private String loginName;
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
	 * 证书类型名称
	 */
	private String credTypeName;

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

	/**
	 * 单位名称
	 */
	private String orgName;

	/**
	 * 是否有操作权限
	 */
	private boolean hasOpr;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCredType() {
		return credType;
	}

	public void setCredType(String credType) {
		this.credType = credType;
	}

	public String getCredNo() {
		return credNo;
	}

	public void setCredNo(String credNo) {
		this.credNo = credNo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getZw() {
		return zw;
	}

	public void setZw(String zw) {
		this.zw = zw;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public boolean isHasOpr() {
		return hasOpr;
	}

	public void setHasOpr(boolean hasOpr) {
		this.hasOpr = hasOpr;
	}

	public String getCredTypeName() {
		return credTypeName;
	}

	public void setCredTypeName(String credTypeName) {
		this.credTypeName = credTypeName;
	}
}
