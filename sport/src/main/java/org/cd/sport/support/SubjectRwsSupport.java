package org.cd.sport.support;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.lang.StringUtils;
import org.cd.sport.domain.Dic;
import org.cd.sport.domain.SubjectRwsAppropriation;
import org.cd.sport.domain.SubjectRwsDevice;
import org.cd.sport.domain.SubjectRwsSchedule;
import org.cd.sport.domain.SubjectRwsUndertaker;
import org.cd.sport.exception.ParameterIsWrongException;
import org.cd.sport.service.DicService;
import org.cd.sport.view.SubjectRwsAppropriationView;
import org.cd.sport.view.SubjectRwsDeviceView;
import org.cd.sport.view.SubjectRwsScheduleView;
import org.cd.sport.view.SubjectRwsUndertakerView;
import org.cd.sport.vo.SubjectRwsUndertakerVo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * 任务书进度安排相关支持类
 * 
 * @author liuyk
 *
 */
public class SubjectRwsSupport extends SportSupport {

	@Autowired
	private DicService dicService;

	public DicService getDicService() {
		return dicService;
	}

	/**
	 * 任务书进度安排
	 */
	public void validate(SubjectRwsScheduleView view) {
		if (view == null) {
			throw new ParameterIsWrongException("任务书进度安排为空");
		}

		if (StringUtils.isBlank(view.getRwsId())) {
			throw new ParameterIsWrongException("任务书进度rwsid不能为空");
		}

		if (StringUtils.isBlank(view.getSubjectId())) {
			throw new ParameterIsWrongException("任务书进度SubjectId不能为空");
		}

		if (StringUtils.isBlank(view.getSchTime())) {
			throw new ParameterIsWrongException("任务书进度安排时间不能为空");
		}
		if (StringUtils.isBlank(view.getWork())) {
			throw new ParameterIsWrongException("任务书进度安排工作内容不能为空");
		}
	}

	/**
	 * 任务书进度安排
	 */
	public void validateUpdate(SubjectRwsScheduleView view) {
		this.validate(view);
		if (StringUtils.isBlank(view.getsId())) {
			throw new ParameterIsWrongException("任务书进度安排id不能为空");
		}
	}

	public SubjectRwsSchedule process(SubjectRwsScheduleView view) {
		this.validate(view);
		SubjectRwsSchedule viewDomain = this.result(SubjectRwsSchedule.class, view);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			java.util.Date parse = sdf.parse(view.getSchTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parse);
			viewDomain.setYear(calendar.get(Calendar.YEAR));
			viewDomain.setMonth(calendar.get(Calendar.MONTH) + 1);
		} catch (ParseException e) {
			throw new ParameterIsWrongException("进度安排时间格式不正确");
		}
		return viewDomain;
	}

	public SubjectRwsSchedule process(SubjectRwsSchedule proposer, SubjectRwsScheduleView view) {
		this.validateUpdate(view);
		if (proposer == null) {
			throw new EntityNotFoundException();
		}
		proposer.setWork(view.getWork());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			java.util.Date parse = sdf.parse(view.getSchTime());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(parse);
			proposer.setYear(calendar.get(Calendar.YEAR));
			proposer.setMonth(calendar.get(Calendar.MONTH) + 1);
		} catch (ParseException e) {
			throw new ParameterIsWrongException("进度安排时间格式不正确");
		}
		proposer.setGoal(view.getGoal());
		return proposer;
	}

	/**
	 * 任务书进度安排
	 */
	public void validate(SubjectRwsDeviceView view) {
		if (view == null) {
			throw new ParameterIsWrongException("任务书设备为空");
		}

		if (StringUtils.isBlank(view.getRwsId())) {
			throw new ParameterIsWrongException("任务书设备rwsid不能为空");
		}

		if (StringUtils.isBlank(view.getSubjectId())) {
			throw new ParameterIsWrongException("任务书设备SubjectId不能为空");
		}

		if (StringUtils.isBlank(view.getName())) {
			throw new ParameterIsWrongException("任务书设备名称不能为空");
		}

		String price = view.getPrice();
		if (StringUtils.isBlank(price)) {
			throw new ParameterIsWrongException("任务书设备单价不能为空");
		} else {
			try {
				BigDecimal p = new BigDecimal(price);
				if (BigDecimal.ZERO.compareTo(p) == 1) {
					throw new ParameterIsWrongException("任务书设备单价不能为负数");
				}
			} catch (Exception e) {
				throw new ParameterIsWrongException("任务书设备单价格式错误");
			}
		}

		String num = view.getNum();
		if (StringUtils.isBlank(num)) {
			throw new ParameterIsWrongException("任务书设备数量不能为空");
		} else {
			try {
				BigDecimal p = new BigDecimal(num);
				if (BigDecimal.ZERO.compareTo(p) == 1) {
					throw new ParameterIsWrongException("任务书设备单价不能为负数");
				}
			} catch (Exception e) {
				throw new ParameterIsWrongException("任务书设备单价格式错误");
			}
		}
	}

	/**
	 * 任务书进度安排
	 */
	public void validateUpdate(SubjectRwsDeviceView view) {
		this.validate(view);
		if (StringUtils.isBlank(view.getdId())) {
			throw new ParameterIsWrongException("任务书设备id不能为空");
		}
	}

	public SubjectRwsDevice process(SubjectRwsDeviceView view) {
		this.validate(view);
		SubjectRwsDevice viewDomain = this.result(SubjectRwsDevice.class, view);
		viewDomain.setPrice(new BigDecimal(view.getPrice()));
		viewDomain.setNum(Integer.parseInt(view.getNum()));
		return viewDomain;
	}

	public SubjectRwsDevice process(SubjectRwsDevice proposer, SubjectRwsDeviceView view) {
		this.validateUpdate(view);
		if (proposer == null) {
			throw new EntityNotFoundException();
		}
		proposer.setName(view.getName());
		proposer.setBuy(view.getBuy());
		proposer.setNorm(view.getNorm());
		proposer.setOrgin(view.getOrgin());
		proposer.setPurpose(view.getPurpose());
		proposer.setSlzs(view.getSlzs());
		proposer.setPrice(new BigDecimal(view.getPrice()));
		proposer.setNum(Integer.parseInt(view.getNum()));
		return proposer;
	}

	/**
	 * 任务书进度安排
	 */
	public void validate(SubjectRwsAppropriationView view) {
		if (view == null) {
			throw new ParameterIsWrongException("任务书进度安排为空");
		}

		if (StringUtils.isBlank(view.getRwsId())) {
			throw new ParameterIsWrongException("任务书进度rwsid不能为空");
		}

		if (StringUtils.isBlank(view.getSubjectId())) {
			throw new ParameterIsWrongException("任务书进度SubjectId不能为空");
		}

		if (StringUtils.isBlank(view.getGainOrg())) {
			throw new ParameterIsWrongException("拨往单位不能为空");
		}

		String approAmount = view.getApproAmount();
		if (!StringUtils.isBlank(approAmount)) {
			try {
				BigDecimal money = new BigDecimal(approAmount);
				if (BigDecimal.ZERO.compareTo(money) == 1) {
					throw new ParameterIsWrongException("拨款金额不能为负数");
				}
			} catch (Exception e) {
				throw new ParameterIsWrongException("拨款金额格式不正确");
			}

		}
	}

	/**
	 * 任务书进度安排
	 */
	public void validateUpdate(SubjectRwsAppropriationView view) {
		this.validate(view);
		if (StringUtils.isBlank(view.getApproId())) {
			throw new ParameterIsWrongException("拨往id不能为空");
		}
	}

	public SubjectRwsAppropriation process(SubjectRwsAppropriationView view) {
		this.validate(view);
		SubjectRwsAppropriation viewDomain = this.result(SubjectRwsAppropriation.class, view);
		BigDecimal money = new BigDecimal(view.getApproAmount());
		viewDomain.setApproAmount(money);
		return viewDomain;
	}

	public SubjectRwsAppropriation process(SubjectRwsAppropriation proposer, SubjectRwsAppropriationView view) {
		this.validateUpdate(view);
		if (proposer == null) {
			throw new EntityNotFoundException();
		}
		proposer.setDescribe(view.getDescribe());
		proposer.setGainOrg(view.getGainOrg());
		BigDecimal money = new BigDecimal(view.getApproAmount());
		proposer.setApproAmount(money);
		return proposer;
	}

	/**
	 * 项目承担人员
	 */
	public void validate(SubjectRwsUndertakerView view) {
		if (view == null) {
			throw new ParameterIsWrongException("任务书项目承担人员为空");
		}

		if (StringUtils.isBlank(view.getRwsId())) {
			throw new ParameterIsWrongException("任务书rwsid不能为空");
		}

		if (StringUtils.isBlank(view.getSubjectId())) {
			throw new ParameterIsWrongException("任务书SubjectId不能为空");
		}

		if (StringUtils.isBlank(view.getOrg())) {
			throw new ParameterIsWrongException("项目承担人员单位不能为空");
		}

	}

	/**
	 * 任务书进度安排
	 */
	public void validateUpdate(SubjectRwsUndertakerView view) {
		this.validate(view);
		if (StringUtils.isBlank(view.getId())) {
			throw new ParameterIsWrongException("项目承担人员id不能为空");
		}
	}

	public SubjectRwsUndertaker process(SubjectRwsUndertakerView view) {
		this.validate(view);
		SubjectRwsUndertaker viewDomain = this.result(SubjectRwsUndertaker.class, view);
		viewDomain.setAge(Integer.parseInt(view.getAge()));
		return viewDomain;
	}

	public SubjectRwsUndertaker process(SubjectRwsUndertaker viewDomain, SubjectRwsUndertakerView view) {
		this.validateUpdate(view);
		if (viewDomain == null) {
			throw new EntityNotFoundException();
		}
		viewDomain.setName(view.getName());
		viewDomain.setDegrees(view.getDegrees());
		viewDomain.setZw(view.getZw());
		viewDomain.setMajor(view.getMajor());
		viewDomain.setWork(view.getWork());
		viewDomain.setType(view.getType());
		viewDomain.setOrg(view.getOrg());
		viewDomain.setAge(Integer.parseInt(view.getAge()));
		return viewDomain;
	}

	public List<SubjectRwsUndertakerVo> processVo(List<SubjectRwsUndertaker> srs) {
		if (srs == null || srs.isEmpty()) {
			return null;
		}

		List<SubjectRwsUndertakerVo> vos = new ArrayList<SubjectRwsUndertakerVo>();
		for (SubjectRwsUndertaker subjectRwsUndertaker : srs) {
			vos.add(this.process(subjectRwsUndertaker));
		}
		return vos;
	}

	public SubjectRwsUndertakerVo process(SubjectRwsUndertaker sr) {
		SubjectRwsUndertakerVo viewDomain = this.result(SubjectRwsUndertakerVo.class, sr);
		viewDomain.setAge(String.valueOf(sr.getAge()));
		Dic dic = dicService.getByCode(sr.getZw());
		if (dic != null) {
			viewDomain.setZw(dic.getName());
		}
		return viewDomain;
	}
}
