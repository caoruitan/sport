package org.cd.sport.vo;

/**
 * 当前登录认证对象
 * 
 * @author liuyk
 *
 */
public class UserQuery {

	private String[] role;

	private String name;

	private String orgId;

	public String[] getRole() {
		return role;
	}

	public void setRole(String[] role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
