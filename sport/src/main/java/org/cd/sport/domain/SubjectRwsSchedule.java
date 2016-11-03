package org.cd.sport.domain;

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
@Table(name = "SPORT_SUBJECT_RWS_SCHEDULE")
public class SubjectRwsSchedule {

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
	private String sId;

	/**
	 * 安排时间
	 */
	private int year;

	/**
	 * 安排时间
	 */
	private int month;

	/**
	 * 工作内容
	 * 
	 */
	private String work;

	/**
	 * 目标
	 */
	private String goal;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "guid")
	@Column(name = "S_ID")
	public String getsId() {
		return sId;
	}

	public void setsId(String sId) {
		this.sId = sId;
	}

	@Column(name = "RWS_ID")
	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
	}

	@Column(name = "S_YEAR")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Column(name = "S_MONTH")
	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Column(name = "WORK")
	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	@Column(name = "GOAL")
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	@Column(name = "SUBJECT_ID")
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

}
