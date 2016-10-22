package org.cd.sport.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity()
@Table(name = "SPORT_SUBJECT")
public class Subject {
	
	private String id;
	
	private String num;
	
	private String name;
	
	private String type;
	
	private String organizationId;
	
	private String organizationName;
	
	private String security;
	
	private String securityName;
	
	private String organizationCount;
	
	private Date beginDate;
	
	private Date endDate;
	
	private String results;
	
	private String resultsName;
	
	private boolean integration;
	
	private String stage;
	
	private Date sbsEndDate;
	
	private Date rwsEndDate;
	
	private Date subjectEndDate;
	
	private String creator;
	
	private String creatorName;
	
	private Date createTime;
	
	private String createUnitId;
	
	private String createUnitName;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	@Column(name = "SUBJECT_ID")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "SUBJECT_NUM")
	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name = "SUBJECT_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "SUBJECT_TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "ORGANIZATION_ID")
	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "ORGANIZATION_NAME")
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Column(name = "SUBJECT_SECURITY")
	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	@Column(name = "SUBJECT_SECURITY_NAME")
	public String getSecurityName() {
		return securityName;
	}

	public void setSecurityName(String securityName) {
		this.securityName = securityName;
	}

	@Column(name = "ORGANIZATION_COUNT")
	public String getOrganizationCount() {
		return organizationCount;
	}

	public void setOrganizationCount(String organizationCount) {
		this.organizationCount = organizationCount;
	}

	@Column(name = "BEGIN_DATE")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "SUBJECT_RESULTS")
	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	@Column(name = "SUBJECT_RESULTS_NAME")
	public String getResultsName() {
		return resultsName;
	}

	public void setResultsName(String resultsName) {
		this.resultsName = resultsName;
	}

	@Column(name = "INTERGRATION")
	public boolean isIntegration() {
		return integration;
	}

	public void setIntegration(boolean integration) {
		this.integration = integration;
	}

	@Column(name = "SUBJECT_STAGE")
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Column(name = "SUBJECT_SBS_END_DATE")
	public Date getSbsEndDate() {
		return sbsEndDate;
	}

	public void setSbsEndDate(Date sbsEndDate) {
		this.sbsEndDate = sbsEndDate;
	}

	@Column(name = "SUBJECT_RWS_END_DATE")
	public Date getRwsEndDate() {
		return rwsEndDate;
	}

	public void setRwsEndDate(Date rwsEndDate) {
		this.rwsEndDate = rwsEndDate;
	}

	@Column(name = "SUBJECT_END_DATE")
	public Date getSubjectEndDate() {
		return subjectEndDate;
	}

	public void setSubjectEndDate(Date subjectEndDate) {
		this.subjectEndDate = subjectEndDate;
	}

	@Column(name = "SUBJECT_CREATOR")
	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Column(name = "SUBJECT_CREATOR_NAME")
	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	@Column(name = "SUBJECT_CREATE_TIME")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "SUBJECT_CREATE_UNIT_ID")
	public String getCreateUnitId() {
		return createUnitId;
	}

	public void setCreateUnitId(String createUnitId) {
		this.createUnitId = createUnitId;
	}

	@Column(name = "SUBJECT_CREATE_UNIT_NAME")
	public String getCreateUnitName() {
		return createUnitName;
	}

	public void setCreateUnitName(String createUnitName) {
		this.createUnitName = createUnitName;
	}
	
}
