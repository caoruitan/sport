package org.cd.sport.support;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.Subject;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.vo.SubjectVo;

public class SubjectSupport extends SportSupport {

	public void validate(SubjectVo subjectVo) {
		if (subjectVo == null) {
			throw new ParameterIsWrongException("课题对象为空");
		}

		if (StringUtils.isBlank(subjectVo.getName())) {
			throw new ParameterIsWrongException("课题名称为空");
		}

		if (StringUtils.isBlank(subjectVo.getType())) {
			throw new ParameterIsWrongException("课题类型为空");
		}

		if (StringUtils.isBlank(subjectVo.getOrganizationId())) {
			throw new ParameterIsWrongException("项目组织单位为空");
		}
	}
	
	public Subject process(SubjectVo subjectVo) {
		this.validate(subjectVo);
		Subject subject = this.result(Subject.class, subjectVo);
		return subject;
	}
	
}
