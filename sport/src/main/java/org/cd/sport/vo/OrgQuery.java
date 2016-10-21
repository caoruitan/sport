package org.cd.sport.vo;

/**
 * 单位查询
 * 
 * @author liuyk
 *
 */
public class OrgQuery {
	/**
	 * 单位注册名称
	 */
	private String fullName;
	/**
	 * 单位法人
	 */
	private String legalLeader;
	/**
	 * 审核状态
	 */
	private String status;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getLegalLeader() {
		return legalLeader;
	}

	public void setLegalLeader(String legalLeader) {
		this.legalLeader = legalLeader;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
