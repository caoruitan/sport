package org.cd.sport.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 任务书进度安排
 * 
 * @author liuyk
 *
 */
@Entity
@Table(name = "SPORT_SUBJECT_RWS_DEVICE")
public class SubjectRwsDevice {

	/**
	 * 课题id
	 */
	private String subjectId;
	/**
	 * 任务书id
	 */
	private String rwsId;

	/**
	 * 主键
	 */
	private String dId;

	/**
	 * 设备名称
	 */
	private String name;

	/**
	 * 用途
	 */
	private String purpose;

	/**
	 * 指标
	 * 
	 */
	private String norm;

	/**
	 * 购置或者试制
	 */
	private String buy;

	/**
	 * 单价
	 */
	private BigDecimal price;

	/**
	 * 购买数量
	 */
	private int num;

	/**
	 * 申请从专项经费中列支数
	 */
	private String slzs;

	/**
	 * 购置地区
	 */
	private String orgin;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	@Column(name = "D_ID")
	public String getdId() {
		return dId;
	}

	public void setdId(String dId) {
		this.dId = dId;
	}

	@Column(name = "RWS_ID")
	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
	}

	@Column(name = "SUBJECT_ID")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "PURPOSE")
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@Column(name = "NORM")
	public String getNorm() {
		return norm;
	}

	public void setNorm(String norm) {
		this.norm = norm;
	}
	
	@Column(name = "BUY")
	public String getBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}
	
	@Column(name = "PRICE")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Column(name = "NUM")
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	@Column(name = "SLZS")
	public String getSlzs() {
		return slzs;
	}

	public void setSlzs(String slzs) {
		this.slzs = slzs;
	}
	
	@Column(name = "ORGIN")
	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}
}
