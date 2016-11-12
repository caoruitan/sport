package org.cd.sport.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "SPORT_SUBJECT_RWS")
public class SubjectRws {

	private String rwsId;

	private String subjectId;

	private String address;

	private String phone;

	private Date startDate;

	private Date endDate;

	/**
	 * 项目协助单位
	 */
	private String cooperateOrg;

	/**
	 * 研究目标和研究内容
	 */
	private String yjmb;
	/**
	 * 项目技术关键和创新点
	 */
	private String jsgj;
	/**
	 * 项目研究方法
	 */
	private String yjff;
	/**
	 * 项目研究实验方案
	 */
	private String syfa;
	/**
	 * 项目预期结果
	 */
	private String yqjg;
	/**
	 * 承担单位现有工作条件和基础
	 */
	private String gztj;
	/**
	 * 经费来源及经费支出情况说明
	 */
	private String ysly;

	private String status;

	private String comment;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	@Column(name = "RWS_ID")
	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String RWSId) {
		this.rwsId = RWSId;
	}

	@Column(name = "SUBJECT_ID")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	@Column(name = "SBS_ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "SBS_PHONE")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "RWS_START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Column(name = "RWS_END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "RWS_COOPERATE_ORG")
	public String getCooperateOrg() {
		return cooperateOrg;
	}

	public void setCooperateOrg(String cooperateOrg) {
		this.cooperateOrg = cooperateOrg;
	}

	@Column(name = "RWS_YJMB")
	public String getYjmb() {
		return yjmb;
	}

	public void setYjmb(String yjmb) {
		this.yjmb = yjmb;
	}

	@Column(name = "RWS_JSGJ")
	public String getJsgj() {
		return jsgj;
	}

	public void setJsgj(String jsgj) {
		this.jsgj = jsgj;
	}

	@Column(name = "RWS_SYFA")
	public String getSyfa() {
		return syfa;
	}

	public void setSyfa(String syfa) {
		this.syfa = syfa;
	}

	@Column(name = "RWS_YQJG")
	public String getYqjg() {
		return yqjg;
	}

	public void setYqjg(String yqjg) {
		this.yqjg = yqjg;
	}

	@Column(name = "RWS_GZTJ")
	public String getGztj() {
		return gztj;
	}

	public void setGztj(String gztj) {
		this.gztj = gztj;
	}

	@Column(name = "RWS_YJFF")
	public String getYjff() {
		return yjff;
	}

	public void setYjff(String yjff) {
		this.yjff = yjff;
	}

	@Column(name = "RWS_STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "RWS_COMMENT")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "RWS_YSLY")
	public String getYsly() {
		return ysly;
	}

	public void setYsly(String ysly) {
		this.ysly = ysly;
	}

}
