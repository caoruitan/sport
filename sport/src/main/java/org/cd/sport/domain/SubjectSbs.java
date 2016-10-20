package org.cd.sport.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity()
@Table(name = "SPORT_SUBJECT_SBS")
public class SubjectSbs {
	
	private String sbsId;
	
	private String subjectId;
	
	private String address;
	
	private String phone;
	
	private String fax;
	
	private String email;
	
	private String years;
	
	private String xtyj;
	
	private String yjmb;
	
	private String jsgj;
	
	private String yjff;
	
	private String syfa;
	
	private String jdap;
	
	private String yqjg;
	
	private String gztj;
	
	private String tjyj;
	
	private String status;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	@Column(name = "SBS_ID")
	public String getSbsId() {
		return sbsId;
	}

	public void setSbsId(String sbsId) {
		this.sbsId = sbsId;
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

	@Column(name = "SBS_FAX")
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "SBS_EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "SBS_YEARS")
	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	@Column(name = "SBS_XTYJ")
	public String getXtyj() {
		return xtyj;
	}

	public void setXtyj(String xtyj) {
		this.xtyj = xtyj;
	}

	@Column(name = "SBS_YJMB")
	public String getYjmb() {
		return yjmb;
	}

	public void setYjmb(String yjmb) {
		this.yjmb = yjmb;
	}

	@Column(name = "SBS_JSGJ")
	public String getJsgj() {
		return jsgj;
	}

	public void setJsgj(String jsgj) {
		this.jsgj = jsgj;
	}

	@Column(name = "SBS_YJFF")
	public String getYjff() {
		return yjff;
	}

	public void setYjff(String yjff) {
		this.yjff = yjff;
	}

	@Column(name = "SBS_SYFA")
	public String getSyfa() {
		return syfa;
	}

	public void setSyfa(String syfa) {
		this.syfa = syfa;
	}

	@Column(name = "SBS_JDAP")
	public String getJdap() {
		return jdap;
	}

	public void setJdap(String jdap) {
		this.jdap = jdap;
	}

	@Column(name = "SBS_YQJG")
	public String getYqjg() {
		return yqjg;
	}

	public void setYqjg(String yqjg) {
		this.yqjg = yqjg;
	}

	@Column(name = "SBS_GZTJ")
	public String getGztj() {
		return gztj;
	}

	public void setGztj(String gztj) {
		this.gztj = gztj;
	}

	@Column(name = "SBS_TJYJ")
	public String getTjyj() {
		return tjyj;
	}

	public void setTjyj(String tjyj) {
		this.tjyj = tjyj;
	}

	@Column(name = "SBS_STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
