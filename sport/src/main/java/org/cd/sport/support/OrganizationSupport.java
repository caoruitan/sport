package org.cd.sport.support;

import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.view.OrganizationView;

/**
 * 
 * 组织相关支持类
 * 
 * @author liuyk
 *
 */
public class OrganizationSupport extends SportSupport {

	/**
	 * 验证用户信息合法性
	 */
	public void validate(OrganizationView dic) {

	}

	public OrganizationDomain process(OrganizationView dic) {
		this.validate(dic);
		return this.result(OrganizationDomain.class, dic);
	}

}
