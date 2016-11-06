package org.cd.sport.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 部门对象
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_ORGANIZATION")
public class OrganizationDomain {
	/**
	 * 单位id
	 */
	private String orgId;
	/**
	 * 单位全称
	 */
	private String fullName;
	/**
	 * 英文名称
	 */
	private String englishName;
	/**
	 * 单位简称
	 */
	private String shortName;
	/**
	 * 单位地址
	 */
	private String address;
	/**
	 * 单位主页
	 */
	private String homepage;
	/**
	 * 法人代表
	 */
	private String legalLeader;
	/**
	 * 所在地区
	 */
	private String region;
	/**
	 * 单位电话
	 */
	private String telphone;
	/**
	 * 单位传真
	 */
	private String fax;
	/**
	 * 单位性质
	 */
	private String quality;
	/**
	 * 单位信箱
	 */
	private String email;
	/**
	 * 组织机构代码
	 */
	private String code;
	/**
	 * 邮政编码
	 */
	private String post;

	/**
	 * 业务负责人姓名
	 */
	private String managerName;
	/**
	 * 业务负责人移动电话
	 */
	private String managerPhone;
	/**
	 * 业务负责人电话
	 */
	private String managerTel;
	/**
	 * 业务负责人传真
	 */
	private String managerfax;
	/**
	 * 业务负责人电子邮箱
	 */
	private String managerEmail;

	/**
	 * 权限单位
	 */
	private int role;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * 审核不通过原因
	 */
	private String reason;
	/**
	 * 注册时间
	 */
	private Timestamp createTime;

	@Id
	@Column(name = "ORG_ID")
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "FULL_NAME")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(name = "ENGLISH_NAME")
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	@Column(name = "SHORT_NAME")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "HOMEPAGE")
	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	@Column(name = "LEGAL_LEADER")
	public String getLegalLeader() {
		return legalLeader;
	}

	public void setLegalLeader(String legalLeader) {
		this.legalLeader = legalLeader;
	}

	@Column(name = "REGION")
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Column(name = "TELPHONE")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "QUALITY")
	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "POST")
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "MANAGER_NAME")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Column(name = "MANAGER_PHONE")
	public String getManagerPhone() {
		return managerPhone;
	}

	public void setManagerPhone(String managerPhone) {
		this.managerPhone = managerPhone;
	}

	@Column(name = "MANAGER_TEL")
	public String getManagerTel() {
		return managerTel;
	}

	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}

	@Column(name = "MANAGER_FAX")
	public String getManagerfax() {
		return managerfax;
	}

	public void setManagerfax(String managerfax) {
		this.managerfax = managerfax;
	}

	@Column(name = "MANAGER_EMAIL")
	public String getManagerEmail() {
		return managerEmail;
	}

	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}

	@Column(name = "STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "CREATE_TIME")
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "ROLE")
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

}