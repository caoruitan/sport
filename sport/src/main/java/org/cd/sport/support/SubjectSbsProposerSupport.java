package org.cd.sport.support;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.view.SubjectSbsProposerView;

/**
 * 
 * 申请人相关支持类
 * 
 * @author liuyk
 *
 */
public class SubjectSbsProposerSupport extends SportSupport {

	/**
	 * 验证申请人信息合法性
	 */
	public void validate(SubjectSbsProposerView view) {
		if (view == null) {
			throw new ParameterIsWrongException("申请人对象为空");
		}

		if (StringUtils.isBlank(view.getName()) || view.getName().length() < 4 || view.getName().length() > 20) {
			throw new ParameterIsWrongException("申请人名为空或者格式不对");
		}

		if (StringUtils.isBlank(view.getZw())) {
			throw new ParameterIsWrongException("申请人职务不能为空");
		}

		if (view.getBirthday() == null) {
			throw new ParameterIsWrongException("申请人出生日期不能为空");
		}
		if (StringUtils.isBlank(view.getDegrees())) {
			throw new ParameterIsWrongException("申请人学历不能为空");
		}
		if (StringUtils.isBlank(view.getMajor())) {
			throw new ParameterIsWrongException("申请人专业不能为空");
		}

		if (StringUtils.isBlank(view.getWork())) {
			throw new ParameterIsWrongException("申请人分工不能为空");
		}

		if (StringUtils.isBlank(view.getBackdrop())) {
			throw new ParameterIsWrongException("申请人研究背景不能为空");
		}
	}

	public SubjectSbsProposer process(SubjectSbsProposerView view) {
		this.validate(view);
		SubjectSbsProposer viewDomain = this.result(SubjectSbsProposer.class, view);
		return viewDomain;
	}
}
