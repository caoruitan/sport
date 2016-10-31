package org.cd.sport.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import org.cd.sport.idclass.SubjectSbsBudgetIdClass;

/**
 * 申报预算
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_SUBJECT_SBS_BUDGET")
@IdClass(SubjectSbsBudgetIdClass.class)
public class SubjectSbsBudget {
	/**
	 * 申报书id
	 */
	private String sbsId;
	/**
	 * 预算编号
	 */
	private String code;
	/**
	 * 预算名称
	 */
	private String name;
	/**
	 * 金额
	 */
	private BigDecimal cost;
	/**
	 * 计算依据和理由
	 */
	private String reason;

	@Id
	@Column(name = "SBS_ID")
	public String getSbsId() {
		return sbsId;
	}

	public void setSbsId(String sbsId) {
		this.sbsId = sbsId;
	}

	@Id
	@Column(name = "CODE")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "COST")
	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
