package org.cd.sport.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 拨往其他单位经费情况填报
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_SUBJECT_APPRO")
public class SubjectRwsAppropriation {
	
	/**
	 * 课题id
	 */
	private String subjectId;
	/**
	 * 任务书id
	 */
	private String rwsId;
	/**
	 * 拨款id
	 */
	private String approId;
	/**
	 * 拨往单位
	 */
	private String gainOrg;
	/**
	 * 拨付数额
	 */
	private BigDecimal approAmount;
	/**
	 * 用途说明
	 */
	private String describe;

	@Id
	@Column(name = "APPRO_Id")
	public String getApproId() {
		return approId;
	}

	public void setApproId(String approId) {
		this.approId = approId;
	}

	@Column(name = "GAIN_ORG")
	public String getGainOrg() {
		return gainOrg;
	}

	public void setGainOrg(String gainOrg) {
		this.gainOrg = gainOrg;
	}

	@Column(name = "APPRO_AMOUNT")
	public BigDecimal getApproAmount() {
		return approAmount;
	}

	public void setApproAmount(BigDecimal approAmount) {
		this.approAmount = approAmount;
	}

	@Column(name = "DESCRIBE")
	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	@Column(name = "SUBJECT_ID")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "RWS_ID")
	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
	}

}
