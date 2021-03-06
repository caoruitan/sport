package org.cd.sport.support;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.OrganizationDomain;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.view.OrganizationView;
import org.cd.sport.vo.OrgVo;

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
		if (StringUtils.isBlank(dic.getFullName())) {
			throw new ParameterIsWrongException("单位全称不能为空");
		}
		if (StringUtils.isBlank(dic.getAddress())) {
			throw new ParameterIsWrongException("单位地址不能为空");
		}
		if (StringUtils.isBlank(dic.getLegalLeader())) {
			throw new ParameterIsWrongException("法人代表不能为空");
		}
		if (StringUtils.isBlank(dic.getRegion())) {
			throw new ParameterIsWrongException("所在区域不能为空");
		}
		if (StringUtils.isBlank(dic.getTelphone())) {
			throw new ParameterIsWrongException("单位电话不能为空");
		}
		if (StringUtils.isBlank(dic.getFax())) {
			throw new ParameterIsWrongException("单位传真不能为空");
		}
		if (StringUtils.isBlank(dic.getQuality())) {
			throw new ParameterIsWrongException("单位性质不能为空");
		}
		if (StringUtils.isBlank(dic.getEmail())) {
			throw new ParameterIsWrongException("组织电子信箱不能为空");
		}
		if (StringUtils.isBlank(dic.getCodePre())) {
			throw new ParameterIsWrongException("组织机构代码不能为空");
		}
		if (StringUtils.isBlank(dic.getCodeSufix())) {
			throw new ParameterIsWrongException("组织机构代码不能为空");
		}
		if (StringUtils.isBlank(dic.getPost())) {
			throw new ParameterIsWrongException("组织邮政编码不能为空");
		}
		if (StringUtils.isBlank(dic.getManagerName())) {
			throw new ParameterIsWrongException("业务负责人姓名不能为空");
		}
		if (StringUtils.isBlank(dic.getTelphone())) {
			throw new ParameterIsWrongException("业务负责人不能为空");
		}

	}

	public void validateUpdate(OrganizationView dic) {
		this.validate(dic);
		if (StringUtils.isBlank(dic.getOrgId())) {
			throw new ParameterIsWrongException("单位id不能为空");
		}
	}

	public OrganizationDomain process(OrganizationView org) {
		this.validate(org);
		OrganizationDomain result = this.result(OrganizationDomain.class, org);
		result.setCode(org.getCodePre() + "-" + org.getCodeSufix());
		return result;
	}

	public List<OrgVo> process(List<OrganizationDomain> orgs) {
		if (orgs == null || orgs.isEmpty()) {
			return null;
		}

		List<OrgVo> vos = new ArrayList<OrgVo>();
		for (OrganizationDomain organizationDomain : orgs) {
			OrgVo process = this.process(organizationDomain);
			vos.add(process);
		}
		return vos;
	}

	public List<OrgVo> processVo(List<OrgVo> orgs) {
		if (orgs == null || orgs.isEmpty()) {
			return null;
		}

		for (OrgVo vo : orgs) {
			vo.setStatusName(Constants.Org.getStatusName(vo.getStatus()));
		}
		return orgs;
	}

	public OrgVo process(OrganizationDomain org) {
		OrgVo result = this.result(OrgVo.class, org);
		result.setStatusName(Constants.Org.getStatusName(org.getStatus()));
		return result;
	}

}
