package org.cd.sport.view;

/**
 * 拨往其他单位经费情况填报
 * 
 * @author liuyk
 *
 */

public class SubjectRwsAppropriationView {
	/**
	 * 课题id
	 */
	private String subjectId;
	/**
	 * 任务书id
	 */
	private String rwsId;
	
	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getRwsId() {
		return rwsId;
	}

	public void setRwsId(String rwsId) {
		this.rwsId = rwsId;
	}

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
	private String approAmount;
	/**
	 * 用途说明
	 */
	private String describe;

	public String getApproId() {
		return approId;
	}

	public void setApproId(String approId) {
		this.approId = approId;
	}

	public String getGainOrg() {
		return gainOrg;
	}

	public void setGainOrg(String gainOrg) {
		this.gainOrg = gainOrg;
	}

	public String getApproAmount() {
		return approAmount;
	}

	public void setApproAmount(String approAmount) {
		this.approAmount = approAmount;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
