package org.cd.sport.support;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.constant.Constants;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.SubjectSbsProposer;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.view.SubjectSbsProposerView;
import org.cd.sport.vo.SubjectSbsProposerVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 申请人相关支持类
 * 
 * @author liuyk
 *
 */
public class SubjectSbsProposerSupport extends SportSupport {

	@Autowired
	private DicService dicService;

	/**
	 * 验证申请人信息合法性
	 */
	public void validate(SubjectSbsProposerView view) {
		if (view == null) {
			throw new ParameterIsWrongException("申请人对象为空");
		}

		if (StringUtils.isBlank(view.getName()) || view.getName().length() > 20) {
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

	/**
	 * 验证申请人信息合法性
	 */
	public void validateUpdate(SubjectSbsProposerView view) {
		this.validate(view);
		if (StringUtils.isBlank(view.getId())) {
			throw new ParameterIsWrongException("申请人id不能为空");
		}
	}

	public SubjectSbsProposer process(SubjectSbsProposerView view) {
		this.validate(view);
		SubjectSbsProposer viewDomain = this.result(SubjectSbsProposer.class, view);
		return viewDomain;
	}

	public SubjectSbsProposer process(SubjectSbsProposer proposer, SubjectSbsProposerView view) {
		this.validateUpdate(view);
		if (proposer == null) {
			throw new EntityNotFoundException();
		}
		proposer.setBackdrop(view.getBackdrop());
		proposer.setWork(view.getWork());
		proposer.setBirthday(view.getBirthday());
		proposer.setDegrees(view.getDegrees());
		proposer.setEmail(view.getEmail());
		proposer.setGender(view.getGender());
		proposer.setMajor(view.getMajor());
		proposer.setName(view.getName());
		proposer.setOrg(view.getOrg());
		proposer.setPhone(view.getPhone());
		proposer.setUniversity(view.getUniversity());
		proposer.setZw(view.getZw());
		return proposer;
	}

	public List<SubjectSbsProposerVo> processVo(List<SubjectSbsProposer> props) {
		if (props == null || props.isEmpty()) {
			return null;
		}

		List<SubjectSbsProposerVo> vos = new ArrayList<SubjectSbsProposerVo>();
		for (SubjectSbsProposer subjectSbsProposer : props) {
			vos.add(this.process(subjectSbsProposer));
		}
		return vos;
	}

	public SubjectSbsProposerVo process(SubjectSbsProposer prop) {
		SubjectSbsProposerVo viewDomain = this.result(SubjectSbsProposerVo.class, prop);
		// 性别和年龄
		viewDomain.setGender(Constants.User.getGenderName(prop.getGender()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Dic dic = this.dicService.getByCode(prop.getZw());
		if (dic != null) {
			viewDomain.setZw(dic.getName());
		}
		Date birthday = prop.getBirthday();
		Calendar c = Calendar.getInstance();
		c.setTime(birthday);
		int year1 = c.get(Calendar.YEAR);
		int month1 = c.get(Calendar.MONTH);

		c.setTime(new Date(System.currentTimeMillis()));
		int year2 = c.get(Calendar.YEAR);
		int month2 = c.get(Calendar.MONTH);

		int result;
		if (year1 == year2) {
			result = month1 - month2;
		} else {
			result = 12 * (year1 - year2) + month1 - month2;
		}
		viewDomain.setAge(result / 12);
		viewDomain.setBirthday(sdf.format(birthday));
		return viewDomain;
	}
}
