package org.cd.sport.support;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ParameterIsWrongException;
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
		if (dic == null) {
			throw new ParameterIsWrongException("组织不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgFullName())) {
			throw new ParameterIsWrongException("单位全称不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgAddress())) {
			throw new ParameterIsWrongException("单位地址不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgLegalLeader())) {
			throw new ParameterIsWrongException("法人代表不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgRegion())) {
			throw new ParameterIsWrongException("所在区域不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgTelphone())) {
			throw new ParameterIsWrongException("单位电话不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgFax())) {
			throw new ParameterIsWrongException("单位传真不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgQuality())) {
			throw new ParameterIsWrongException("单位性质不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgEmail())) {
			throw new ParameterIsWrongException("组织电子信箱不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgCode())) {
			throw new ParameterIsWrongException("组织机构代码不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgPost())) {
			throw new ParameterIsWrongException("组织邮政编码不能为空");
		}
		if (StringUtils.isBlank(dic.getManagerName())) {
			throw new ParameterIsWrongException("业务负责人姓名不能为空");
		}
		if (StringUtils.isBlank(dic.getOrgTelphone())) {
			throw new ParameterIsWrongException("业务负责人不能为空");
		}

	}

	public void validateUpdate(OrganizationView dic) {
		this.validate(dic);
		if (StringUtils.isBlank(dic.getOrgId())) {
			throw new ParameterIsWrongException("单位id不能为空");
		}
	}

	public OrganizationDomain process(OrganizationView dic) {
		this.validate(dic);
		return this.result(OrganizationDomain.class, dic);
	}

}
