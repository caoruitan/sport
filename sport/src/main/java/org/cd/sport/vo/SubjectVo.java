package org.cd.sport.vo;

import java.util.Date;

public class SubjectVo {
	
	private String id;
	
	private String name;
	
	private String type;
	
	private String organizationId;
	
	private String security;
	
	private String organizationCount;
	
	private Date beginDate;
	
	private Date endDate;
	
	private String results;
	
	private boolean integration;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getOrganizationCount() {
		return organizationCount;
	}

	public void setOrganizationCount(String organizationCount) {
		this.organizationCount = organizationCount;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public boolean isIntegration() {
		return integration;
	}

	public void setIntegration(boolean integration) {
		this.integration = integration;
	}
	
}
