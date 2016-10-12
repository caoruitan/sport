package org.cd.sport.domain;

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
	private String orgFullName;
	/**
	 * 英文名称
	 */
	private String orgEnglishName;
	/**
	 * 单位简称
	 */
	private String orgShortName;
	/**
	 * 单位地址
	 */
	private String orgAddress;
	/**
	 * 单位主页
	 */
	private String orgHomepage;
	/**
	 * 法人代表
	 */
	private String orgLegalLeader;
	/**
	 * 所在地区
	 */
	private String orgRegion;
	/**
	 * 单位电话
	 */
	private String orgTelphone;
	/**
	 * 单位传真
	 */
	private String orgFax;
	/**
	 * 单位性质
	 */
	private String orgQuality;
	/**
	 * 单位信箱
	 */
	private String orgEmail;
	/**
	 * 组织机构代码
	 */
	private String orgCode;
	/**
	 * 邮政编码
	 */
	private String orgPost;

	/**
	 * 业务负责人姓名
	 */
	private String managerName;
	/**
	 * 业务负责人移动电话
	 */
	private String managerMobilePhone;
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

	@Id
	@Column(name = "ORGANIZATION_ID")
	@GenericGenerator(name = "systemUUID", strategy = "uuid")
	@GeneratedValue(generator = "systemUUID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name = "ORG_NAME")
	public String getOrgFullName() {
		return orgFullName;
	}

	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}

	@Column(name = "ORG_ENGLISHNAME")
	public String getOrgEnglishName() {
		return orgEnglishName;
	}

	public void setOrgEnglishName(String orgEnglishName) {
		this.orgEnglishName = orgEnglishName;
	}

	@Column(name = "ORG_SHORTNAME")
	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	@Column(name = "ORG_ADDRESS")
	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	@Column(name = "ORG_HOMEPAGE")
	public String getOrgHomepage() {
		return orgHomepage;
	}

	public void setOrgHomepage(String orgHomepage) {
		this.orgHomepage = orgHomepage;
	}

	@Column(name = "ORG_LEGALLEADER")
	public String getOrgLegalLeader() {
		return orgLegalLeader;
	}

	public void setOrgLegalLeader(String orgLegalLeader) {
		this.orgLegalLeader = orgLegalLeader;
	}

	@Column(name = "ORG_REGION")
	public String getOrgRegion() {
		return orgRegion;
	}

	public void setOrgRegion(String orgRegion) {
		this.orgRegion = orgRegion;
	}

	@Column(name = "ORG_TELPHONE")
	public String getOrgTelphone() {
		return orgTelphone;
	}

	public void setOrgTelphone(String orgTelphone) {
		this.orgTelphone = orgTelphone;
	}

	@Column(name = "ORG_FAX")
	public String getOrgFax() {
		return orgFax;
	}

	public void setOrgFax(String orgFax) {
		this.orgFax = orgFax;
	}

	@Column(name = "ORG_QUAILTY")
	public String getOrgQuality() {
		return orgQuality;
	}

	public void setOrgQuality(String orgQuality) {
		this.orgQuality = orgQuality;
	}

	@Column(name = "ORG_EMAIL")
	public String getOrgEmail() {
		return orgEmail;
	}

	public void setOrgEmail(String orgEmail) {
		this.orgEmail = orgEmail;
	}

	@Column(name = "ORG_CODE")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	@Column(name = "ORG_POST")
	public String getOrgPost() {
		return orgPost;
	}

	public void setOrgPost(String orgPost) {
		this.orgPost = orgPost;
	}

	@Column(name = "MANAGER_NAME")
	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	@Column(name = "MANAGER_MOBILEPHONE")
	public String getManagerMobilePhone() {
		return managerMobilePhone;
	}

	public void setManagerMobilePhone(String managerMobilePhone) {
		this.managerMobilePhone = managerMobilePhone;
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

}
